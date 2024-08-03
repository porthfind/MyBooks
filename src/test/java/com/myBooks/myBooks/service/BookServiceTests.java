package com.myBooks.myBooks.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.myBooks.myBooks.dto.BookDTO;
import com.myBooks.myBooks.model.Book;
import com.myBooks.myBooks.repository.BookRepository;

public class BookServiceTests {
	
	@Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;
	
	@Test
    public void BookService_DeleteBookById_ReturnVoid() {
        int bookId = 1;
        Book book = Book.builder().name("As serviçais").isbn("9789896374952").startedDate(LocalDate.now()).build();

        when(bookRepository.findById(bookId)).thenReturn(Optional.ofNullable(book));
        doNothing().when(bookRepository).delete(book);

        assertAll(() -> bookService.deleteById(bookId));
    }
	
	@Test
    public void BookService_FindById_ReturnBookDTO() {
        int bookId = 1;
        Book book = Book.builder().name("As serviçais").isbn("9789896374952").startedDate(LocalDate.now()).build();
        when(bookRepository.findById(bookId)).thenReturn(Optional.ofNullable(book));

        BookDTO bookReturn = bookService.findById(bookId);

        Assertions.assertThat(bookReturn).isNotNull();
    }
	
	/**TODO: faltam restantes testes**/

}
