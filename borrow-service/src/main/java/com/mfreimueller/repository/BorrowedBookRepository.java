package com.mfreimueller.repository;

import com.mfreimueller.domain.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Long> {
    Optional<BorrowedBook> findByUserIdAndIsbn(Long userId, String isbn);
    Optional<BorrowedBook> findByIsbn(String isbn);

    Stream<BorrowedBook> findAllByUserId(Long userId);
}
