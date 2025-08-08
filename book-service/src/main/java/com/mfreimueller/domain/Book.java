package com.mfreimueller.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.ISBN;

import java.time.LocalDate;

/// A single book, uniquely identified by its ISBN. In retrospective,
/// the layout of the model is less than optimal, as our library doesn't
/// support multiple copies of the same book. Therefore, we are faced
/// with two choices:
/// - Introduce a surrogate key, or
/// - introduce a new domain model 'Copy'.
///
/// In favor of option two, we would have less data duplication, as
/// the core information about copies of the same book could be retrieved
/// from one place.
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
