package com.mfreimueller.controller;

import com.mfreimueller.domain.Book;
import com.mfreimueller.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/// A controller that handles everything related to book CRUD-operations.
/// The following operations are yet to be implemented:
/// - filter options for getAllBooks
/// - update
/// - delete (implement as deleted-flag)
@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }
}
