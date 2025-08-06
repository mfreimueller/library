package com.mfreimueller.dto;

import java.time.LocalDate;

public record BookDto(String isbn, String edition, String title, String author, LocalDate publishDate, String genre) {
}
