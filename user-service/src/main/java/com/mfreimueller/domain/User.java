package com.mfreimueller.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/// We try to store as much information as necessary about our users, within sensible bounds, regarding the
/// amount of data necessary to be able to reach out to users that failed to return books in time, or that
/// damaged copies of our books.
/// In theory, we store the birthday of users to introduce an age check when borrowing books.
/// Additionally, we support an info-field, where library workers can store information about users, for example,
/// to explain why the `canBorrow`-flag has been set to false, which therefor prevents users from borrowing any books.
/// Notice, that we don't store any password or credentials. We assume, for simplicity, that our application is
/// only used by the library workers and not the users themselves :-)
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

    private String info;

    private boolean canBorrow;

    /// Returns true iff the deletedAt timestamp isn't set (i.e. is null).
    public boolean isActive() {
        return deletedAt == null;
    }
}
