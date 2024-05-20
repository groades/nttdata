package com.groades.nttdata.controller;

import com.groades.nttdata.response.AuthenticationResponse;
import com.groades.nttdata.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.time.LocalDateTime;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AuthenticationService authenticationService;

    @BeforeEach
    public void init(){
        when(authenticationService.register(any())).thenReturn(new AuthenticationResponse(
                UUID.randomUUID(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                "",
                "",
                true));
    }
    @Test
    public void registerEndpointTest() throws Exception {

        String requestBody = "{\"name\": \"Juan Rodriguez\"," +
                "\"email\": \"juan@rodriguez.org\"," +
                "\"password\": \"Ha22\"," +
                "\"phones\": " +
                "[{\"number\": \"1234567\"," +
                "\"citycode\": \"1\"," +
                "\"countrycode\": \"57\"}]}";

        mockMvc.perform(post("/api/v1/authentication/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    public void emptyEmailExceptionTest() throws Exception {
        String requestBody = "{\"name\": \"Juan Rodriguez\"," +
                "\"password\": \"Ha22\"," +
                "\"phones\": " +
                "[{\"number\": \"1234567\"," +
                "\"citycode\": \"1\"," +
                "\"countrycode\": \"57\"}]}";

        mockMvc.perform(post("/api/v1/authentication/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException()
                    instanceof MethodArgumentNotValidException));;
    }
    @Test
    public void emailFormatExceptionTest() throws Exception {
        String requestBody = "{\"name\": \"Juan Rodriguez\"," +
                "\"email\": \"juanrodriguez.org\"," +
                "\"password\": \"Ha22\"," +
                "\"phones\": " +
                "[{\"number\": \"1234567\"," +
                "\"citycode\": \"1\"," +
                "\"countrycode\": \"57\"}]}";

        mockMvc.perform(post("/api/v1/authentication/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException()
                    instanceof MethodArgumentNotValidException));;
    }
    @Test
    public void PasswordFormatExceptionTest() throws Exception {
        String requestBody = "{\"name\": \"Juan Rodriguez\"," +
                "\"email\": \"juan@rodriguez.org\"," +
                "\"password\": \"Ha2\"," +
                "\"phones\": " +
                "[{\"number\": \"1234567\"," +
                "\"citycode\": \"1\"," +
                "\"countrycode\": \"57\"}]}";

        mockMvc.perform(post("/api/v1/authentication/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException()
                        instanceof MethodArgumentNotValidException));
    }

}
