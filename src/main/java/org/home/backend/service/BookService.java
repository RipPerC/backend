package org.home.backend.service;

import org.home.backend.model.Book;
import org.home.backend.model.BookSage;
import org.home.backend.repository.BookRepository;
import org.home.backend.repository.BookSageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookSageRepository sageRepository;

    public BookService(BookRepository bookRepository, BookSageRepository sageRepository) {
        this.bookRepository = bookRepository;
        this.sageRepository = sageRepository;
    }

    //BOOKS
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book save(Book book){
        return bookRepository.save(book);
    }

    public boolean deleteBook(long id) {

        if (bookRepository.findById(id).isPresent()){
            Book book = bookRepository.findById(id).get();

            bookRepository.deleteById(id);

            if (book.getSage() != null && bookRepository.howManyBookInASage(book.getSage().getId()) <= 1) {
                deleteSage(book.getSage().getId());
            }
            return true;

        } else {
            return false;
        }
    }

    private boolean hasBooksWithSameSage(long sageId) {
        return bookRepository.howManyBookInASage(sageId) != 0;
    }

    // BOOK SAGES
    public void createOrUpdateSage(BookSage sage){
        sageRepository.save(sage);
    }

    public List<BookSage> getAllSages() {
        List<BookSage> sages = new ArrayList<>(sageRepository.findAll());
        sages.sort(Comparator.comparing(BookSage::getName));
        return sages;
    }

    public BookSage getSageById(long id){
        return (sageRepository.findById(id).isPresent())? sageRepository.findById(id).get() : null;
    }

    public Boolean deleteSage(long id) {

        if(!hasBooksWithSameSage(id)) {
            sageRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}