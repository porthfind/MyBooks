package com.myBooks.myBooks.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.myBooks.myBooks.dto.BookDTO;
import com.myBooks.myBooks.model.Book;
import com.myBooks.myBooks.repository.BookRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService{
	
	private final RestTemplate restTemplate;
	private final BookRepository bookRepository;
	
	public void saveBook(Book book) {
		bookRepository.save(book);
		log.info("Book {} is save.", book.getId());
	}
	
	public void deleteByIdRepository(int bookId) {
		bookRepository.deleteById(bookId);
		log.info("Book {} was deleted.", bookId);
	}
	
	public BookDTO findByIdRepository(int bookId) {
		return convertEntityToDto(bookRepository.findById(bookId).get());
	}
	
	/**TODO: Mudar codigo**/
	public List<BookDTO> getAllBooksFromUser(String username){
		if(username != null) {
			List<Book> books = bookRepository.findByUsername(username);
			List<BookDTO> listBooks = new ArrayList<>();
			
			for(Book book : books) {
				BookDTO bookDto = convertEntityToDto(book);
				listBooks.add(bookDto);
			}
			
			return listBooks;
		}
		else
			return List.of();
	}
	
	//-- Convert Entity to DTO --//
	
	private BookDTO convertEntityToDto(Book book) {
		BookDTO bookDto = new BookDTO();
		
		bookDto.setId(book.getId());
		bookDto.setComment(book.getComment());
		bookDto.setEndDate(book.getEndDate());
		bookDto.setIsbn(book.getIsbn());
		bookDto.setName(book.getName());
		bookDto.setStartedDate(book.getStartedDate());
		
		return bookDto;
	}
	
	//--Validations-//

    public boolean isValidISBN(String isbn) {
	    String url = UriComponentsBuilder.fromHttpUrl("https://www.googleapis.com/books/v1/volumes")
	            .queryParam("q", "isbn:" + isbn)
	            .toUriString();
	
	    try {
	        String response = restTemplate.getForObject(url, String.class);
	        return response != null && response.contains("\"totalItems\": 1");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
    }
	
	public boolean isStartReadingDateValid(LocalDate startReadingDate) {
		if(startReadingDate.isAfter(LocalDate.now()))
			return false;
		
		return true;
	}
	
	public boolean isStartReadingDateAfterEndReadingDate(LocalDate startReadingDate, LocalDate endReadingDate){
		if(endReadingDate == null)
			return false;
		else {
			if(startReadingDate.isAfter(endReadingDate))
				return true;
			else
				return false;
		}
			
	}
	
}
