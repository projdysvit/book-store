package com.book.store.bookstore.service.impl;

import com.book.store.bookstore.dto.request.user.UserRegistrationRequestDto;
import com.book.store.bookstore.dto.response.user.UserResponseDto;
import com.book.store.bookstore.exception.RegistrationException;
import com.book.store.bookstore.mapper.UserMapper;
import com.book.store.bookstore.repository.UserRepository;
import com.book.store.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto create(UserRegistrationRequestDto requestDto) {
        if (userRepository.findByEmail(requestDto.email()).isPresent()) {
            throw new RegistrationException("User with that email already exists: "
                                            + requestDto.email());
        }

        return userMapper.toDto(userRepository.save(userMapper.toModel(requestDto)));
    }
}
