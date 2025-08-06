package com.mfreimueller.mapper;

import com.mfreimueller.domain.Book;
import com.mfreimueller.dto.BookDto;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface BookMapper extends Converter<Book, BookDto> {
    BookDto toDto(Book book);
}
