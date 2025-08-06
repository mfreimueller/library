package com.mfreimueller.mapper;

import com.mfreimueller.domain.BorrowedBook;
import com.mfreimueller.dto.BookDto;
import com.mfreimueller.dto.BorrowedBookDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface BorrowedBookMapper {
    BorrowedBookDto toDto(BorrowedBook borrowedBook);
}
