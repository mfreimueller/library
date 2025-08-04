package com.mfreimueller.mapper;

import com.mfreimueller.domain.User;
import com.mfreimueller.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends Converter<User, UserDto> {
}
