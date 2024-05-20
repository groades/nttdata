package com.groades.nttdata.service;

import com.groades.nttdata.common.DuplicatedUserException;
import com.groades.nttdata.entities.PhoneEntity;
import com.groades.nttdata.entities.UserEntity;
import com.groades.nttdata.repository.TokenRepository;
import com.groades.nttdata.repository.UserRepository;
import com.groades.nttdata.request.UserRegisterRequest;
import com.groades.nttdata.response.AuthenticationResponse;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;
    @Mock
    private TokenRepository tokenRepository;

    @InjectMocks
    private AuthenticationService authService;


    UserRegisterRequest req = UserRegisterRequest.builder()
            .name("")
            .email("")
            .phones(new ArrayList<PhoneEntity>())
            .password("")
            .build();

    UserDetails user = UserEntity.builder()
            .id(UUID.fromString("bbcc4621-d88f-4a94-ae2f-b38072bf5087"))
            .created(LocalDateTime.of(2024,01,01,01,01))
            .modified(LocalDateTime.of(2024,01,01,01,01))
            .name("")
            .email("")
            .password("")
            .phones(List.of(new PhoneEntity()))
            .isactive(true)
            .last_login(LocalDateTime.of(2024,01,01,01,01))
            .build();

    AuthenticationResponse authResponse = AuthenticationResponse.builder()
            .id(UUID.fromString("bbcc4621-d88f-4a94-ae2f-b38072bf5087"))
            .created(LocalDateTime.of(2024,01,01,01,01))
            .modified(LocalDateTime.of(2024,01,01,01,01))
            .last_login(LocalDateTime.of(2024,01,01,01,01))
            .token("")
            .refreshToken("")
            .isactive(true)
            .build();

    @BeforeEach
    public void init(){
        when(passwordEncoder.encode(any())).thenReturn("Pass");
        when(userRepository.save(any())).thenReturn(user);
        when(jwtService.generateToken(any())).thenReturn("");
        when(jwtService.generateRefreshToken(any())).thenReturn("");
    }

    @Test
    public void registerExistingUserExceptionTest(){
        Optional<UserEntity> user = Optional.of(UserEntity.builder().id(UUID.randomUUID()).build());
        when(userRepository.findByEmail(any())).thenReturn(user);
        assertThrows(DuplicatedUserException.class,() -> authService.register(req));
    }

    @Test
    public void registerTest(){
        when(userRepository.findByEmail(any())).thenReturn(Optional.empty());
        assertEquals(authResponse,authService.register(req));
    }

}
