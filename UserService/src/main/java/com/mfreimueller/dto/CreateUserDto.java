package com.mfreimueller.dto;

import java.time.LocalDate;

public record CreateUserDto(String firstName, String lastName, String email, LocalDate birthday) {

}
