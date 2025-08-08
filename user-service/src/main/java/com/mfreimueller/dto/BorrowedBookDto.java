package com.mfreimueller.dto;

import java.time.LocalDate;

public record BorrowedBookDto(Long id, Long bookId, Long userId, LocalDate borrowedAt, LocalDate returnedAt) {
    /// A book is borrowed as long as it has not been returned :-)
    public boolean isBorrowed() {
        return returnedAt == null;
    }
}
