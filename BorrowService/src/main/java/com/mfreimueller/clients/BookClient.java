package com.mfreimueller.clients;

import com.mfreimueller.dto.BookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "book-service")
public interface BookClient {
    @GetMapping("/books/{id}")
    BookDto getBookById(@PathVariable Long id);
}
