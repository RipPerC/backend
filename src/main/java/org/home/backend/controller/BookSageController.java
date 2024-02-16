package org.home.backend.controller;

import org.home.backend.model.BookSage;
import org.home.backend.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/v1/book")
public class BookSageController {

    private final BookService bookService;

    public BookSageController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/sage")
    public ResponseEntity<BookSage> createOrUpdateSage(@RequestBody BookSage sage) {

        boolean newSage = sage.getId() == 0;

        try {
            bookService.createOrUpdateSage(sage);

            if (newSage) {
                return new ResponseEntity<>(sage, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(sage, HttpStatus.ACCEPTED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sage")
    public ResponseEntity<List<BookSage>> getAllSages() {

        try {

            List<BookSage> sages = bookService.getAllSages();
            return new ResponseEntity<>(sages, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sage/{id}")
    public ResponseEntity<BookSage> getSageById(@PathVariable("id") long id){
        try {
            BookSage sage = bookService.getSageById(id);

            if (sage == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(sage, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/sage/{id}")
    public ResponseEntity<HttpStatus> deleteBookSage(@PathVariable("id") long id) {
        try {

            if(bookService.deleteSage(id)) {
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            } else {
                //Books with this sage
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}