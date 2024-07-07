package com.myBooks.myBooks.book;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;


@Service
public class BookService {
	
	private static List<Book> books = new ArrayList<>();
	
	public List<Book> getBooks(){
		return books;
	}
	
	public void deleteById(int id) {
		Predicate<? super Book> predicate = book -> book.getId() == id;
		books.removeIf(predicate);
	}
	
	public Book findById(int id) {
		Predicate<? super Book> predicate = book -> book.getId() == id;
		return books.stream().filter(predicate).findFirst().get();
	}
	
	public void updateBook(@Valid Book book) {
		deleteById(book.getId());
		books.add(book);
	}
	
	public void addBook(@Valid Book book) {
		books.add(book);
	}
	
	/**TODO: Log **/

}
