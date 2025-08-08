package com.mfreimueller.mapper;

import com.mfreimueller.domain.BorrowedBook;
import com.mfreimueller.dto.BookDto;
import com.mfreimueller.dto.BorrowedBookDetailsDto;
import com.mfreimueller.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface BorrowedBookDetailsMapper {

    @Mappings({
            @Mapping(source = "borrowedBook.id", target = "id"),
            @Mapping(source = "borrowedBook.isbn", target = "isbn"),
            @Mapping(source = "bookDto.title", target = "title"),
            @Mapping(source = "borrowedBook.userId", target = "userId"),
            @Mapping(expression = "java(userDto.firstName() + \" \" + userDto.lastName())", target = "username"),
            @Mapping(source = "userDto.email", target = "email"),
            @Mapping(source = "borrowedBook.borrowedAt", target = "borrowedAt"),
            @Mapping(target = "returnedAt", ignore = true)
    })
    BorrowedBookDetailsDto toDto(BorrowedBook borrowedBook, UserDto userDto, BookDto bookDto);
}
