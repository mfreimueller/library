package com.mfreimueller.repository.specifications;

import com.mfreimueller.domain.Book;
import com.mfreimueller.dto.BookFilterDto;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookSpecification {
    public static Specification<Book> fromFilter(BookFilterDto filter) {
        List<Specification<Book>> specs = new ArrayList<>();

        specs.add(hasIsbn(filter.isbn()));
        specs.add(hasEdition(filter.edition()));
        specs.add(hasTitle(filter.title()));
        specs.add(hasAuthor(filter.author()));
        specs.add(hasFrom(filter.from()));
        specs.add(hasFrom(filter.to()));
        specs.add(hasGenre(filter.genre()));

        return Specification.allOf(specs);
    }

    private static Specification<Book> hasIsbn(String isbn) {
        return (root, query, cb) -> isbn == null ? null : cb.like(root.get("isbn"), isbn);
    }

    private static Specification<Book> hasEdition(String edition) {
        return (root, query, cb) -> edition == null ? null : cb.equal(root.get("edition"), edition);
    }

    private static Specification<Book> hasTitle(String title) {
        return (root, query, cb) -> title == null ? null : cb.like(root.get("title"), title);
    }

    private static Specification<Book> hasAuthor(String author) {
        return (root, query, cb) -> author == null ? null : cb.like(root.get("author"), author);
    }

    private static Specification<Book> hasFrom(LocalDate from) {
        return (root, query, cb) -> from == null ? null : cb.greaterThanOrEqualTo(root.get("publishDate"), from);
    }

    private static Specification<Book> hasTo(LocalDate to) {
        return (root, query, cb) -> to == null ? null : cb.greaterThanOrEqualTo(root.get("publishDate"), to);
    }

    private static Specification<Book> hasGenre(String genre) {
        return (root, query, cb) -> genre == null ? null : cb.equal(root.get("genre"), genre);
    }
}
