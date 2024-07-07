package com.myBooks.myBooks.book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer>{
	
	public List<Book> findByUsername(String username);

}
