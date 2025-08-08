package com.mfreimueller.mapper;

import com.mfreimueller.domain.User;
import com.mfreimueller.dto.UpdateUserDto;
import com.mfreimueller.dto.UserDto;
import org.mapstruct.*;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface UserMapper extends Converter<User, UserDto> {
    UserDto toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
    void updateUserFromDto(UpdateUserDto dto, @MappingTarget User entity);
}
