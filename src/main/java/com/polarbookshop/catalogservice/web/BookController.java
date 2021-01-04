package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.backup.BookServiceBak;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("books")
public class BookController {

    private final BookServiceBak bookServiceBak;

    public BookController(BookServiceBak bookServiceBak) {
        this.bookServiceBak = bookServiceBak;
    }

    @GetMapping
    public Collection<Book> get() {
        return bookServiceBak.viewBookList();
    }

    @GetMapping("{isbn}")
    public Book getByIsbn(@PathVariable String isbn) {
        return bookServiceBak.viewBookDetails(isbn).orElse(null);
    }

    @PostMapping
    public Book post(@Valid @RequestBody Book book) {
        return bookServiceBak.addBookToCatalog(book);
    }

    @DeleteMapping("{isbn}")
    public Book delete(@PathVariable String isbn) {
        return bookServiceBak.removeBookFromCatalog(isbn);
    }

    @PutMapping("{isbn}")
    public Book put(@PathVariable String isbn, @RequestBody @Valid Book book) {
        return bookServiceBak.editBookDetails(book);
    }
}
