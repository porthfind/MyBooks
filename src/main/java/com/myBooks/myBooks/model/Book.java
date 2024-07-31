package com.myBooks.myBooks.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {
	
	@Id
	@GeneratedValue
	private int id;
	
	@NotBlank(message = "Name can not be null.")
	@Size(min=2, max=50, message = "Name must be between {min} and {max} characters long.")
	private String name;
	
	@NotBlank(message = "Isbn can not be null.")
	private String isbn;
	
	@NotNull(message = "Started Date can not be null.")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate startedDate;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate endDate;
	
	@Size(max=50, message = "Comment must be {max} characters long.")
	private String comment;
	
	private String username;
	
}
