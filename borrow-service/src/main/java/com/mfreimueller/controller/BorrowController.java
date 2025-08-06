package com.mfreimueller.controller;

import com.mfreimueller.dto.BorrowedBookDto;
import com.mfreimueller.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrowings")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;

    @GetMapping("/user/{userId}")
    public List<BorrowedBookDto> getBorrowedBooksForUser(@PathVariable Long userId) {
        Assert.notNull(userId, "userId must not be null.");
        return borrowService.getAllBorrowedBooksOfUser(userId);
    }

    @GetMapping("/book/{isbn}")
    public BorrowedBookDto getBorrowedBookForIsbn(@PathVariable String isbn) {
        Assert.notNull(isbn, "userId must not be null.");
        return borrowService.getAllBorrowedBookForIsbn(isbn);
    }

    @PutMapping("/{userId}/{isbn}")
    public BorrowedBookDto borrowBook(@PathVariable Long userId, @PathVariable String isbn) {
        Assert.notNull(userId, "userId must not be null.");
        Assert.notNull(isbn, "isbn must not be null.");

        return borrowService.borrowBook(userId, isbn);
    }

    @DeleteMapping("/{userId}/{isbn}")
    public BorrowedBookDto returnBorrowedBook(@PathVariable Long userId, @PathVariable String isbn) {
        Assert.notNull(userId, "userId must not be null.");
        Assert.notNull(isbn, "isbn must not be null.");

        return borrowService.returnBorrowedBook(userId, isbn);
    }
}
