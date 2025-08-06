package com.mfreimueller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record UpdateBookDto(String edition, String title, String author, @PastOrPresent LocalDate publishDate, String genre) {
}
