package com.book.store.bookstore.service.impl;

import com.book.store.bookstore.dto.request.user.UserRegistrationRequestDto;
import com.book.store.bookstore.dto.response.user.UserResponseDto;
import com.book.store.bookstore.exception.EntityNotFoundException;
import com.book.store.bookstore.exception.RegistrationException;
import com.book.store.bookstore.mapper.UserMapper;
import com.book.store.bookstore.model.Role;
import com.book.store.bookstore.model.User;
import com.book.store.bookstore.repository.RoleRepository;
import com.book.store.bookstore.repository.UserRepository;
import com.book.store.bookstore.service.UserService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto create(UserRegistrationRequestDto requestDto) {
        if (userRepository.findByEmail(requestDto.email()).isPresent()) {
            throw new RegistrationException("User with that email already exists: "
                                            + requestDto.email());
        }

        User newUser = userMapper.toModel(requestDto);
        newUser.setPassword(passwordEncoder.encode(requestDto.password()));
        newUser.setRoles(Set.of(
                roleRepository.findByName(Role.RoleName.ROLE_USER)
                    .orElseThrow(
                        () -> new EntityNotFoundException("Role with name \'" 
                            + Role.RoleName.ROLE_USER.name() + "\' wasn\'t found")
                    )
            )
        );

        return userMapper.toDto(userRepository.save(newUser));
    }
}
