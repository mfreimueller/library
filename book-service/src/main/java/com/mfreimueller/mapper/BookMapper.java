package com.mfreimueller.mapper;

import com.mfreimueller.domain.Book;
import com.mfreimueller.dto.BookDto;
import com.mfreimueller.dto.UpdateBookDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDto toDto(Book book);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
    void updateBookFromDto(UpdateBookDto dto, @MappingTarget Book entity);
}
