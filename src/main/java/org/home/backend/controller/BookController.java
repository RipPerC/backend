package org.home.backend.controller;

import org.home.backend.model.Book;
import org.home.backend.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/v1")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {

        System.out.println(book);

        try {
            Book _book = bookService.save(book);

            if (book.getId() != 0) {
                return new ResponseEntity<>(book, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(_book, HttpStatus.CREATED);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/book")
    public ResponseEntity<List<Book>> getAll() {

        try {

            List<Book> books = bookService.getAllBooks();

            System.out.println(books);

            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
