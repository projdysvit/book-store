package com.book.store.bookstore.service.security;

import com.book.store.bookstore.dto.request.user.UserLoginRequestDto;
import com.book.store.bookstore.dto.response.user.UserLoginResponseDto;
import com.book.store.bookstore.util.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public UserLoginResponseDto authenticate(UserLoginRequestDto requestDto) {
        final Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                requestDto.email(), requestDto.password()
            )
        );
        
        return new UserLoginResponseDto(jwtUtil.generateToken(authentication.getName()));
    }
}
