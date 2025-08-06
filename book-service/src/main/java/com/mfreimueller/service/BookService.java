package com.mfreimueller.service;

import com.mfreimueller.domain.Book;
import com.mfreimueller.dto.BookDto;
import com.mfreimueller.dto.CreateBookDto;
import com.mfreimueller.repository.BookRepository;
import jakarta.persistence.RollbackException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ConversionService conversionService;

    public List<BookDto> getAll() {
        return bookRepository.findAll().stream().map(b -> conversionService.convert(b, BookDto.class)).toList();
    }

    public BookDto createBook(CreateBookDto createBookDto) {
        Assert.notNull(createBookDto, "createBookDto must not be null.");

        Book book = new Book(createBookDto.isbn(), createBookDto.edition(), createBookDto.title(),
                createBookDto.author(), createBookDto.publishDate(), createBookDto.genre());

        return conversionService.convert(bookRepository.save(book), BookDto.class);
    }
}
