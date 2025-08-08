package com.mfreimueller.dto;

import java.time.LocalDate;

public record BorrowedBookDto(Long id, Long bookId, Long userId, LocalDate borrowedAt, LocalDate returnedAt) {
    public boolean isBorrowed() {
        return returnedAt == null;
    }
}
