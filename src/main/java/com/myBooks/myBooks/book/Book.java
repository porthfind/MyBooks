package com.myBooks.myBooks.book;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Book {
	
	@Id
	@GeneratedValue
	private int id;
	
	@NotBlank(message = "Name can not be null.")
	@Size(min=2, max=50, message = "Name must be between {min} and {max} characters long.")
	private String name;
	
	@NotBlank(message = "Isbn can not be null.")
	//@IsbnValidation
	private String isbn;
	
	@NotNull(message = "Started Date can not be null.")
	private LocalDate startedDate;
	
	private LocalDate endDate;
	
	@Size(max=50, message = "Comment must be {max} characters long.")
	private String comment;
	
	private String username;
	
	public Book(int id, String name, String isbn, LocalDate startedDate, LocalDate endDate, String comment, String username) {
		super();
		this.id = id;
		this.name = name;
		this.isbn = isbn;
		this.startedDate = startedDate;
		this.endDate = endDate;
		this.comment = comment;
		this.username = username;
	}
	
	public Book() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public LocalDate getStartedDate() {
		return startedDate;
	}

	public void setStartedDate(LocalDate startedDate) {
		this.startedDate = startedDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
