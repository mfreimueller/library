package com.mfreimueller.service;

import com.mfreimueller.client.BorrowClient;
import com.mfreimueller.domain.Book;
import com.mfreimueller.dto.*;
import com.mfreimueller.exception.EntityNotFoundException;
import com.mfreimueller.exception.InvalidStateException;
import com.mfreimueller.mapper.BookMapper;
import com.mfreimueller.repository.BookRepository;
import com.mfreimueller.repository.specifications.BookSpecification;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class BookService {
    @Autowired
    private BorrowClient borrowClient;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookMapper bookMapper;

    public BookDto getOne(final String isbn) {
        return bookRepository.findByIsbn(isbn).map(bookMapper::toDto).orElseThrow();
    }

    public Page<BookDto> getAll(final BookFilterDto filter, final Pageable pageable) {
        val specs = BookSpecification.fromFilter(filter);
        return bookRepository.findAll(specs, pageable).map(bookMapper::toDto);
    }

    public BookDto createBook(final CreateBookDto createBookDto) {
        Assert.notNull(createBookDto, "createBookDto must not be null.");

        Book book = new Book(createBookDto.isbn(), createBookDto.edition(), createBookDto.title(),
                createBookDto.author(), createBookDto.publishDate(), createBookDto.genre());

        return bookMapper.toDto(bookRepository.save(book));
    }

    public BookDto updateBook(final String isbn, final UpdateBookDto updateBookDto) {
        Assert.notNull(isbn, "isbn must not be null.");
        Assert.notNull(updateBookDto, "updateBookDto must not be null.");

        var book = bookRepository.findByIsbn(isbn).orElseThrow();
        bookMapper.updateBookFromDto(updateBookDto, book);

        return bookMapper.toDto(bookRepository.save(book));
    }

    /// Deletes a book identified by a ISBN.
    /// This operation can fail if:
    /// - the book has been borrowed by a user
    /// - the isbn belongs to no book
    public void deleteBook(final String isbn) {
        Assert.notNull(isbn, "isbn must not be null.");

        val borrows = borrowClient.getBorrowedBooksByBookId(isbn);
        if (borrows.stream().anyMatch(BorrowedBookDto::isBorrowed)) {
            throw new InvalidStateException(isbn, "The book has been borrowed by a user.");
        }

        bookRepository.deleteByIsbn(isbn);
    }
}
