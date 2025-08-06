package com.mfreimueller.dto;

public record BookDto(String isbn, String edition, String title, String author, Integer year, String genre) {
}
