package com.myBooks.myBooks.isbn;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ISBNService {

	private final RestTemplate restTemplate;

    public ISBNService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

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
}
