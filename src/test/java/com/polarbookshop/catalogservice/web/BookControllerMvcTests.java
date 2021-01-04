package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.backup.BookServiceBak;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BookController.class)
public class BookControllerMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookServiceBak bookServiceBak;

    @Test
    void whenReadingNotExistingBookThenShouldReturn404() throws Exception {
        String isbn = "73737313940";
        given(bookServiceBak.viewBookDetails(isbn)).willReturn(Optional.empty());
        mockMvc
                .perform(get("/books/" + isbn).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("The book with ISBN " + isbn + " was not found."));
    }
}
