package com.mfreimueller.dto;

import java.time.LocalDate;

public record BorrowedBookDto(Long id, String isbn, Long userId, LocalDate borrowedAt, LocalDate returnedAt) {
}
