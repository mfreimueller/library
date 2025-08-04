package com.mfreimueller.dto;

import java.time.LocalDate;

public record BorrowedBookDto(Long id, Long bookId, String bookTitle, Long userId, String username, LocalDate borrowedAt) {
}
