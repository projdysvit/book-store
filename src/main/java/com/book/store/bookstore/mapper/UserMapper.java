package com.book.store.bookstore.mapper;

import com.book.store.bookstore.config.MapperConfig;
import com.book.store.bookstore.dto.request.user.UserRegistrationRequestDto;
import com.book.store.bookstore.dto.response.user.UserResponseDto;
import com.book.store.bookstore.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toDto(User user);
    
    @Mapping(target = "id", ignore = true)
    User toModel(UserRegistrationRequestDto requestDto);
}
