package com.groades.nttdata.service;

import com.groades.nttdata.common.TokenType;
import com.groades.nttdata.entities.TokenEntity;
import com.groades.nttdata.entities.UserEntity;
import com.groades.nttdata.repository.TokenRepository;
import com.groades.nttdata.repository.UserRepository;
import com.groades.nttdata.request.UserRegisterRequest;
import com.groades.nttdata.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;

    public AuthenticationResponse register(UserRegisterRequest request) {
        var user = UserEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phones(request.getPhones())
                .build();

        Optional<UserEntity> opUser = userRepository.findByEmail(user.getEmail());
        var savedUser = !opUser.isPresent()?
                userRepository.save(user):
                opUser.get();
        System.out.println(savedUser.toString());
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);

        return AuthenticationResponse.builder()
                .id(savedUser.getId())
                .created(savedUser.getCreated())
                .modified(savedUser.getModified())
                .last_login(savedUser.getLast_login())
                .token(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserToken(UserEntity user, String jwtToken) {
        var token = TokenEntity.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

}
