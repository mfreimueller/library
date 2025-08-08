package com.mfreimueller.dto;

import java.time.LocalDate;

public record BorrowedBookDetailsDto(Long id, String isbn, String title, Long userId, String username, String email, LocalDate borrowedAt, LocalDate returnedAt) implements Comparable<BorrowedBookDetailsDto> {
    @Override
    public int compareTo(BorrowedBookDetailsDto b) {
        return borrowedAt.compareTo(b.borrowedAt);
    }
}
