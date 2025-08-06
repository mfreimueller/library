package com.mfreimueller.service;

import com.mfreimueller.client.BookClient;
import com.mfreimueller.client.UserClient;
import com.mfreimueller.domain.BorrowedBook;
import com.mfreimueller.dto.BorrowedBookDto;
import com.mfreimueller.exception.EntityNotFoundException;
import com.mfreimueller.exception.InvalidStateException;
import com.mfreimueller.mapper.BorrowedBookMapper;
import com.mfreimueller.repository.BorrowedBookRepository;
import jakarta.transaction.Transactional;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowService {
    @Autowired
    private BookClient bookClient;
    @Autowired
    private UserClient userClient;
    @Autowired
    private BorrowedBookRepository borrowedBookRepository;
    @Autowired
    private BorrowedBookMapper borrowedBookMapper;

    /// Attempt to borrow a book by a user. This method can fail for various reasons:
    /// - Either the user or the book don't exist, or
    /// - the book in question is already borrowed, or
    /// - the user still has some borrowed books outstanding and missed their deadline (tbd), or
    /// - the user fails to satisfy the books age requirement (tbd).
    public BorrowedBookDto borrowBook(Long userId, String isbn) {
        Assert.notNull(userId, "userId must not be null.");
        Assert.notNull(isbn, "isbn must not be null.");

        try {
            userClient.getUserById(userId);
        } catch (Exception ex) {
            throw new EntityNotFoundException(userId.toString(), "User");
        }

        try {
            bookClient.getBookByIsbn(isbn);
        } catch (Exception ex) {
            throw new EntityNotFoundException(isbn, "book");
        }

        val borrowed = borrowedBookRepository.findByIsbn(isbn);
        if (borrowed.isPresent()) {
            throw new InvalidStateException(isbn, "The book has already been borrowed by user %d".formatted(borrowed.get().getUserId()));
        }

        val borrow = new BorrowedBook(null, isbn, userId, LocalDate.now(), null);
        return borrowedBookMapper.toDto(borrowedBookRepository.save(borrow));
    }

    public BorrowedBookDto returnBorrowedBook(Long userId, String isbn) {
        val borrowed = borrowedBookRepository.findByUserIdAndIsbn(userId, isbn).orElseThrow();
        borrowed.setReturnedAt(LocalDate.now());

        return borrowedBookMapper.toDto(borrowedBookRepository.save(borrowed));
    }

    @Transactional()
    public List<BorrowedBookDto> getAllBorrowedBooksOfUser(Long userId) {
        return borrowedBookRepository.findAllByUserId(userId)
                .map(borrowedBookMapper::toDto).toList();
    }

    public BorrowedBookDto getAllBorrowedBookForIsbn(String isbn) {
        return borrowedBookRepository.findByIsbn(isbn).map(borrowedBookMapper::toDto).orElseThrow();
    }
}
