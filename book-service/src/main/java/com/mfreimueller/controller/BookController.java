package com.mfreimueller.controller;

import com.mfreimueller.dto.BookDto;
import com.mfreimueller.dto.BookFilterDto;
import com.mfreimueller.dto.CreateBookDto;
import com.mfreimueller.dto.UpdateBookDto;
import com.mfreimueller.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{isbn}")
    public BookDto getBook(@PathVariable String isbn) { return bookService.getOne(isbn); }

    @GetMapping
    public Page<BookDto> getAllBooks(@ModelAttribute BookFilterDto filter, Pageable pageable) {
        return bookService.getAll(filter, pageable);
    }

    @PostMapping
    public BookDto createBook(@Valid @RequestBody CreateBookDto createBookDto) {
        return bookService.createBook(createBookDto);
    }

    @PostMapping("/{isbn}")
    public BookDto updateBook(@PathVariable String isbn, @Valid @RequestBody UpdateBookDto updateBookDto) {
        return bookService.updateBook(isbn, updateBookDto);
    }

    @DeleteMapping("/{isbn}")
    public void deleteBook(@PathVariable String isbn) {
        bookService.deleteBook(isbn);
    }
}
