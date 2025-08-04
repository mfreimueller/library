package com.mfreimueller.repository;

import com.mfreimueller.domain.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Long> {
    BorrowedBook findByUserIdAndBookId(Long userId, Long bookId);
    BorrowedBook findByBookId(Long bookId);

    Stream<BorrowedBook> findAllByUserId(Long userId);
}
