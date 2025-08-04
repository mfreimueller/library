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

    @GetMapping("/{userId}")
    public List<BorrowedBookDto> getBorrowedBooksForUser(@PathVariable Long userId) {
        Assert.notNull(userId, "userId must not be null.");
        return borrowService.getAllBorrowedBooksOfUser(userId);
    }

    @PutMapping("/{userId}/{bookId}")
    public BorrowedBookDto borrowBook(@PathVariable Long userId, @PathVariable Long bookId) {
        Assert.notNull(userId, "userId must not be null.");
        Assert.notNull(bookId, "bookId must not be null.");

        return borrowService.borrowBook(userId, bookId);
    }

    @DeleteMapping("/{userId}/{bookId}")
    public BorrowedBookDto returnBorrowedBook(@PathVariable Long userId, @PathVariable Long bookId) {
        Assert.notNull(userId, "userId must not be null.");
        Assert.notNull(bookId, "bookId must not be null.");

        return borrowService.returnBorrowedBook(userId, bookId);
    }
}
