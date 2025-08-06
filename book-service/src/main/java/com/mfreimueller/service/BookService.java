package com.mfreimueller.service;

import com.mfreimueller.domain.Book;
import com.mfreimueller.dto.BookDto;
import com.mfreimueller.dto.BookFilterDto;
import com.mfreimueller.dto.CreateBookDto;
import com.mfreimueller.mapper.BookMapper;
import com.mfreimueller.repository.BookRepository;
import com.mfreimueller.repository.specifications.BookSpecification;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookMapper bookMapper;

    public BookDto getOne(String isbn) {
        return bookRepository.findByIsbn(isbn).map(bookMapper::toDto).orElseThrow();
    }

    public Page<BookDto> getAll(BookFilterDto filter, Pageable pageable) {
        val specs = BookSpecification.fromFilter(filter);
        return bookRepository.findAll(specs, pageable).map(bookMapper::toDto);
    }

    public BookDto createBook(CreateBookDto createBookDto) {
        Assert.notNull(createBookDto, "createBookDto must not be null.");

        Book book = new Book(createBookDto.isbn(), createBookDto.edition(), createBookDto.title(),
                createBookDto.author(), createBookDto.publishDate(), createBookDto.genre());

        return bookMapper.toDto(bookRepository.save(book));
    }
}
