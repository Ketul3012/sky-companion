package com.sky.companion.authentication.login.service.impl;

import com.sky.companion.authentication.common.entity.UserEntity;
import com.sky.companion.authentication.common.repository.UserRepository;
import com.sky.companion.authentication.login.request.LoginRequest;
import com.sky.companion.authentication.login.response.LoginResponse;
import com.sky.companion.authentication.login.service.LoginServiceImpl;
import com.sky.companion.common.constants.ResponseStatusCodeConstants;
import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.security.jwt.JWTUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LoginServiceImplTest {

    @InjectMocks
    private LoginServiceImpl loginService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JWTUtils jwtUtils;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testLoginSuccess() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("email");
        loginRequest.setPassword("password");
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setFirstName("abc");
        userEntity.setLastName("vx");
        userEntity.setEmail("email");
        userEntity.setPassword("hashed_password");
        Mockito.when(userRepository.findByEmail(loginRequest.getEmail())).thenReturn(Optional.of(userEntity));
        Mockito.when(passwordEncoder.matches(loginRequest.getPassword(), userEntity.getPassword())).thenReturn(true);

        // Act
        HttpSuccessResponse<LoginResponse> response = loginService.login(loginRequest);

        // Assert
        assertEquals(ResponseStatusCodeConstants.SUCCESS, response.getStatusCode());

    }

    @Test
    void testLoginInvalidEmailAddress() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("email");
        loginRequest.setPassword("password");
        Mockito.when(userRepository.findByEmail(loginRequest.getEmail())).thenReturn(Optional.empty());

        // Act
        HttpSuccessResponse<LoginResponse> response = loginService.login(loginRequest);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatusCode());
    }

    @Test
    void testInActiveUser() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("email");
        loginRequest.setPassword("password");
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setFirstName("abc");
        userEntity.setLastName("vx");
        userEntity.setEmail("email");
        userEntity.setPassword("hashed_password");
        userEntity.setActive(false);
        Mockito.when(userRepository.findByEmail(loginRequest.getEmail())).thenReturn(Optional.of(userEntity));

        // Act
        HttpSuccessResponse<LoginResponse> response = loginService.login(loginRequest);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
    }

    @Test
    void testLoginInvalidPassword() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("email");
        loginRequest.setPassword("password");
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setFirstName("abc");
        userEntity.setLastName("vx");
        userEntity.setEmail("email");
        userEntity.setPassword("hashed_password");
        Mockito.when(userRepository.findByEmail(loginRequest.getEmail())).thenReturn(Optional.of(userEntity));
        Mockito.when(passwordEncoder.matches(loginRequest.getPassword(), userEntity.getPassword())).thenReturn(false);

        // Act
        HttpSuccessResponse<LoginResponse> response = loginService.login(loginRequest);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatusCode());
    }

    @Test
    void testLoginException() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("email");
        loginRequest.setPassword("password");
        Mockito.when(userRepository.findByEmail(loginRequest.getEmail())).thenThrow(new DataAccessResourceFailureException("Database connection failed"));

        // Act & Assert
        assertThrows(DataAccessResourceFailureException.class, () -> loginService.login(loginRequest));
    }
}