package com.myBooks.myBooks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myBooks.myBooks.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{
	
	public List<Book> findByUsername(String username);

}
