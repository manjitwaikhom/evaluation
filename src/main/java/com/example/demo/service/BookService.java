package com.example.demo.service;


import com.example.demo.db.Book;
import com.example.demo.db.BookRepository;
import com.example.demo.google.GoogleBookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(BookDTO bookDTO){
        Book book = new Book(bookDTO.getId(),bookDTO.getTitle(),bookDTO.getAuthor(),bookDTO.getPageCount());
        bookRepository.save(book);
        return book;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
