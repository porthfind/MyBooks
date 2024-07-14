package com.myBooks.myBooks.book;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;


@Service
public class BookService{
	
	private static List<Book> books = new ArrayList<>();
	//private static final String BASE_URL = "https://openlibrary.org/api/books?bibkeys=ISBN:";
	
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
	
	private boolean validateISBN(String isbn) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String url = BASE_URL + isbn + "&format=json";
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            // Verifica se o corpo da resposta não está vazio, o que indica que o ISBN é válido
            String responseBody = response.body().string();
            return !responseBody.equals("{}");
        }
    }*/
	/**TODO:Isbn Validation**/
	
	/**TODO: Log **/

}
