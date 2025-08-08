package com.mfreimueller.mapper;

import com.mfreimueller.domain.BorrowedBook;
import com.mfreimueller.dto.BorrowedBookDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BorrowedBookMapper {
    BorrowedBookDto toDto(BorrowedBook borrowedBook);
}
