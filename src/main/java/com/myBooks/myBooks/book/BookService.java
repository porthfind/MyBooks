package com.myBooks.myBooks.book;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;


@Service
public class BookService{
	
	private final RestTemplate restTemplate;
	private static List<Book> books = new ArrayList<>();
	
    public BookService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
	
	public List<Book> getBooks(){
		return books;
	}
	
	public void deleteById(int id) {
		Predicate<? super Book> predicate = book -> book.getId() == id;
		books.removeIf(predicate);
	}
	
	public Book findById(int id) {
		Predicate<? super Book> predicate = book -> book.getId() == id;
		Book book = books.stream().filter(predicate).findFirst().get();
		return book;
	}
	
	public void updateBook(@Valid Book book) {
		deleteById(book.getId());
		books.add(book);
	}
	
	public void addBook(@Valid Book book) {
		books.add(book);
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
	
	/**TODO:Isbn Validation**/
	/*public boolean isIsbnValid(String isbn) {
		
		try {
            boolean isValid = validateISBN(isbn);
            if (isValid) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return false;
	}
	
*/
	/**TODO:Isbn Validation**/
	
	/**TODO: Log **/

}
