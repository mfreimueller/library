package com.mfreimueller.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.ISBN;

import java.time.LocalDate;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Book {
    @Id
    @ISBN
    private String isbn;

    private String edition;

    private String title;
    private String author;

    @Temporal(TemporalType.DATE)
    private LocalDate publishDate;

    private String genre; // TODO: Maybe move genre into a separate table?
}
