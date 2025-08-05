package com.mfreimueller.service;

import com.mfreimueller.client.BookClient;
import com.mfreimueller.client.UserClient;
import com.mfreimueller.domain.BorrowedBook;
import com.mfreimueller.dto.BorrowedBookDto;
import com.mfreimueller.exception.EntityNotFoundException;
import com.mfreimueller.exception.InvalidStateException;
import com.mfreimueller.repository.BorrowedBookRepository;
import jakarta.transaction.Transactional;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowService {
    @Autowired
    private BookClient bookClient;
    @Autowired
    private UserClient userClient;
    @Autowired
    private BorrowedBookRepository borrowedBookRepository;
    @Autowired
    private ConversionService conversionService;

    /// Attempt to borrow a book by a user. This method can fail for various reasons:
    /// - Either the user or the book don't exist, or
    /// - the book in question is already borrowed, or
    /// - the user still has some borrowed books outstanding and missed their deadline (tbd), or
    /// - the user fails to satisfy the books age requirement (tbd).
    public BorrowedBookDto borrowBook(Long userId, Long bookId) {
        try {
            userClient.getUserById(userId);
        } catch (Exception ex) {
            throw new EntityNotFoundException(userId, "User");
        }

        try {
            bookClient.getBookById(bookId);
        } catch (Exception ex) {
            throw new EntityNotFoundException(bookId, "book");
        }

        val borrowed = borrowedBookRepository.findByBookId(bookId);
        if (borrowed != null) {
            throw new InvalidStateException(bookId, "The book has already been borrowed by user %d".formatted(borrowed.getUserId()));
        }

        val borrow = new BorrowedBook(null, bookId, userId, LocalDate.now(), null);
        return conversionService.convert(borrowedBookRepository.save(borrow), BorrowedBookDto.class);
    }

    public BorrowedBookDto returnBorrowedBook(Long userId, Long bookId) {
        val borrowed = borrowedBookRepository.findByUserIdAndBookId(userId, bookId);
        if (borrowed == null) {
            throw new InvalidStateException(bookId, "The book has not been borrowed by user %d".formatted(userId));
        }

        borrowed.setReturnedAt(LocalDate.now());
        return conversionService.convert(borrowedBookRepository.save(borrowed), BorrowedBookDto.class);
    }

    @Transactional()
    public List<BorrowedBookDto> getAllBorrowedBooksOfUser(Long userId) {
        return borrowedBookRepository.findAllByUserId(userId)
                .map(bb -> conversionService.convert(bb, BorrowedBookDto.class))
                .collect(Collectors.toList());
    }
}
