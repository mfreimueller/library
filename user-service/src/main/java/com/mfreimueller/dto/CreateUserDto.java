package com.mfreimueller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record CreateUserDto(@NotEmpty String firstName, @NotEmpty String lastName, @Email String email, @Past LocalDate birthday) {

}
