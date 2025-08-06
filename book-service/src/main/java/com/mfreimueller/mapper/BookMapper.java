package com.mfreimueller.mapper;

import com.mfreimueller.domain.Book;
import com.mfreimueller.dto.BookDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDto toDto(Book book);
}
