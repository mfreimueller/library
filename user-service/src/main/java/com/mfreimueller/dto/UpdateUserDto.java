package com.mfreimueller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record UpdateUserDto(String firstName, String lastName, @Email String email, @Past LocalDate birthday, boolean canBorrow, String info) {

}
