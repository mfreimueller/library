package com.mfreimueller.client;

import com.mfreimueller.dto.BorrowedBookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "borrow-service")
public interface BorrowClient {
    @GetMapping("/borrowings/user/{userId}")
    List<BorrowedBookDto> getBorrowedBooksByUserId(@PathVariable Long userId);
}
