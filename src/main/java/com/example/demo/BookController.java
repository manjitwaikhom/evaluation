package com.example.demo;

import com.example.demo.db.Book;
import com.example.demo.db.BookRepository;
import com.example.demo.exception.AuthorAbsentException;
import com.example.demo.exception.PageCountAbsentException;
import com.example.demo.exception.TitleAbsentException;
import com.example.demo.google.GoogleBook;
import com.example.demo.google.GoogleBookService;
import com.example.demo.service.BookDTO;
import com.example.demo.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
public class BookController {

    private final GoogleBookService googleBookService;
    private final BookService bookService;

    @Autowired
    public BookController( GoogleBookService googleBookService,BookService bookService) {
        this.googleBookService = googleBookService;
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/google")
    public GoogleBook searchGoogleBooks(@RequestParam("q") String query,
                                        @RequestParam(value = "maxResults", required = false) Integer maxResults,
                                        @RequestParam(value = "startIndex", required = false) Integer startIndex) {

        return googleBookService.searchBooks(query, maxResults, startIndex);
    }

    @PostMapping("/books/{googleId}")
    public ResponseEntity<Book> addBookwithIdToPersonalList(@PathVariable String googleId, @RequestBody BookDTO bookDto) {

        Book bookToSave = null;
        Book book =null;
        if (bookDto.getAuthor() == null) {
            //throw exception
            throw new AuthorAbsentException("Author cannot be null");

        }else if (bookDto.getTitle() == null) {
            //throw exception
            throw new TitleAbsentException("Title cannot be null");

        }else if (bookDto.getPageCount()== null) {
            //throw exception
            throw new PageCountAbsentException("PageCount cannot be null");

        }else{
            bookDto.setId(googleId);
            book = bookService.addBook(bookDto);
        }

        return new ResponseEntity<Book>(book, HttpStatus.CREATED);

    }
}
