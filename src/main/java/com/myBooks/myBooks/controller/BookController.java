package com.myBooks.myBooks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.myBooks.myBooks.dto.BookDTO;
import com.myBooks.myBooks.model.Book;
import com.myBooks.myBooks.service.BookService;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class BookController {
	
	@Autowired
	BookService bookService;

	private String getLoggedInUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String goToListBooksPage(ModelMap model) {
		
		List<BookDTO> books = bookService.getAllBooksFromUser(getLoggedInUsername());
		model.addAttribute("books", books);
		model.put("name", getLoggedInUsername());
		
		return "listBooks";
	}
	
	@RequestMapping("list-books")
	public String listAllBooks(ModelMap model) {
		List<BookDTO> books = bookService.getAllBooksFromUser(getLoggedInUsername());
		
		model.addAttribute("books", books);
		
		return "listBooks";
	}
	
	/*-------------------Add/Update Books---------------------------------*/
	@RequestMapping(value = "add-book", method = RequestMethod.GET)
	public String showNewBookPage(ModelMap model) {
		Book book = new Book();
		model.put("book", book);
		model.put("operation", "add");
		return "addUpdateBook";
	}
	
	@PostMapping(value={"add-book", "update-book"})
	public String addNewBook(ModelMap model, @Valid Book book, BindingResult result) { 
		
		if(result.hasErrors()) {
			return "addUpdateBook";
		}
		else if(!bookService.isValidISBN(book.getIsbn())) {
			result.rejectValue("isbn", "message.isbnInvalid");
			return "addUpdateBook";
		}
		else if(!bookService.isStartReadingDateValid(book.getStartedDate())) {
			result.rejectValue("isbn", "message.startReadingDateInvalid");
			return "addUpdateBook";
		}
		else if(bookService.isStartReadingDateAfterEndReadingDate(book.getStartedDate(), book.getEndDate())) {
			result.rejectValue("isbn", "message.startReadingDateAfterEndReadingDate");
			return "addUpdateBook";
		}
					
		book.setUsername(getLoggedInUsername());
		
		bookService.saveBook(book);
		return "redirect:list-books";
	}
	
	/*-------------------Delete Books---------------------------------*/
	@RequestMapping(value = "delete-book", method = RequestMethod.GET)
	public String deleteBook(@RequestParam int id) {
		//Delete
		bookService.deleteById(id);
		return "redirect:list-books";
	}
	
	/*-------------------Update Books---------------------------------*/
	@RequestMapping(value = "update-book", method = RequestMethod.GET)
	public String showUpdateBookPage(@RequestParam int id, ModelMap model) {
		
		BookDTO book = bookService.findById(id);
		model.put("book", book);
		return "addUpdateBook";
	}
	
	/*-------------------Details Books---------------------------------*/
	@RequestMapping(value = "details-book", method = RequestMethod.GET)
	public String bookDetail(@RequestParam int id, ModelMap model) {
		
		BookDTO bookDTO = bookService.findById(id);
		model.put("book", bookDTO);
		return "detailsBook";
	}

}
