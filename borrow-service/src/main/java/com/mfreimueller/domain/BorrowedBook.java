package com.mfreimueller.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
