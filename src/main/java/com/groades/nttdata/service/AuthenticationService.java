package com.groades.nttdata.service;

import com.groades.nttdata.entities.UserEntity;
import com.groades.nttdata.request.UserRegisterRequest;
import com.groades.nttdata.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    public AuthenticationResponse register(UserRegisterRequest request) {
        var user = UserEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        return AuthenticationResponse.builder()
                .accessToken("jwtToken")
                .refreshToken("refreshToken")
                .build();
    }

}
