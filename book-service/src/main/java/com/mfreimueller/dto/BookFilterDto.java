package com.mfreimueller.dto;

import java.time.LocalDate;

public record BookFilterDto(String isbn, String edition, String title, String author, LocalDate from, LocalDate to, String genre) {
}
