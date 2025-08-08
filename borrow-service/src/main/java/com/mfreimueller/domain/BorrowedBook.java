package com.mfreimueller.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/// Basically this is a compound model, which combines information from the book- and user-services. In theory,
/// we could implement this using a NoSQL database, as we cannot ensure data integrity anyway.
/// Any book that is borrowed in our application receives an entry as BorrowedBook. We try to limit the amount
/// of information we keep here, as this is mostly duplicated information.
/// If a book is returned by its borrower, we set the `returnedAt` property to the current date. One could argue
/// that this property should be updatable, for example, if a book is returned during a power outage.
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class BorrowedBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isbn;
    private Long userId;

    @Temporal(TemporalType.DATE)
    private LocalDate borrowedAt;

    @Temporal(TemporalType.DATE)
    private LocalDate returnedAt;
}
