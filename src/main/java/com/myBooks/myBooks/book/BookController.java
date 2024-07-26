package com.myBooks.myBooks.book;

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

import com.myBooks.myBooks.isbn.ISBNService;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class BookController {

	private BookRepository bookRepository;
	
	public BookController(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}
	
	@Autowired
	ISBNService isbnService;

	private String getLoggedInUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String goToListBooksPage(ModelMap model) {
		
		List<Book> books = bookRepository.findByUsername(getLoggedInUsername());
		model.addAttribute("books", books);
		model.put("name", getLoggedInUsername());
		
		return "listBooks";
	}
	
	@RequestMapping("list-books")
	public String listAllBooks(ModelMap model) {
		List<Book> books = bookRepository.findByUsername(getLoggedInUsername());
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
		else if(!isbnService.isValidISBN(book.getIsbn())) {
			result.rejectValue("isbn", "error.book", "ISBN is not valid. Please insert a valid ISBN.");
			return "addUpdateBook";
		}
					
		book.setUsername(getLoggedInUsername());
		
		bookRepository.save(book);
		return "redirect:list-books";
	}
	
	/*-------------------Delete Books---------------------------------*/
	@RequestMapping(value = "delete-book", method = RequestMethod.GET)
	public String deleteBook(@RequestParam int id) {
		//Delete
		bookRepository.deleteById(id);
		return "redirect:list-books";
	}
	
	/*-------------------Update Books---------------------------------*/
	@RequestMapping(value = "update-book", method = RequestMethod.GET)
	public String showUpdateBookPage(@RequestParam int id, ModelMap model) {
		
		Book book = bookRepository.findById(id).get();
		model.put("book", book);
		return "addUpdateBook";
	}

}
