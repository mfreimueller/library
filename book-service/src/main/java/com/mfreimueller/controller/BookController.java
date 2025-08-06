package com.mfreimueller.controller;

import com.mfreimueller.domain.Book;
import com.mfreimueller.dto.BookDto;
import com.mfreimueller.dto.CreateBookDto;
import com.mfreimueller.repository.BookRepository;
import com.mfreimueller.service.BookService;
import jakarta.validation.Valid;
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
    private BookService bookService;

    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.getAll();
    }

    @PostMapping
    public BookDto createBook(@Valid @RequestBody CreateBookDto createBookDto) {
        return bookService.createBook(createBookDto);
    }
}
