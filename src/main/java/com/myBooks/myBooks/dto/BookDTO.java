package com.myBooks.myBooks.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {

	private int id;
	private String name;
	private String isbn;
	private LocalDate startedDate;
	private LocalDate endDate;
	private String comment;
}
