package com.mfreimueller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record CreateBookDto(@NotEmpty String isbn, @NotEmpty String edition, @NotEmpty String title,
                            @NotEmpty String author, @PastOrPresent LocalDate publishDate, @NotEmpty String genre) {
}
