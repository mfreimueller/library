package com.mfreimueller.dto;

public record BookDto(Long id, String isbn, String edition, String title, String author, Integer year, String genre) {
}
