package com.mfreimueller.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    private String email;

    @Temporal(TemporalType.DATE)
    private LocalDate birthday;

    @Temporal(TemporalType.DATE)
    private LocalDate deletedAt;

    /// Returns true iff the deletedAt timestamp isn't set (i.e. is null).
    public boolean isActive() {
        return deletedAt == null;
    }
}
