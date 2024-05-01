package com.sky.companion.authentication.signup.service;

import com.sky.companion.authentication.common.entity.UserEntity;
import com.sky.companion.authentication.common.repository.UserRepository;
import com.sky.companion.authentication.email_verification.service.EmailVerificationService;
import com.sky.companion.authentication.signup.request.SignUpRequest;
import com.sky.companion.authentication.signup.response.SignUpResponse;
import com.sky.companion.common.constants.ResponseStatusCodeConstants;
import com.sky.companion.common.model.HttpSuccessResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class SignUpServiceTest {
    @InjectMocks
    private SignUpServiceImpl signUpService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailVerificationService emailVerificationService;


    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSignUpSuccess() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setFirstName("John");
        signUpRequest.setLastName("Doe");
        signUpRequest.setEmail("john@example.com");
        signUpRequest.setPassword("password");
        signUpRequest.setConfirmPassword("password");
        signUpRequest.setDob(new Date());
        signUpRequest.setMobileNumber("1234567890");

        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());

        HttpSuccessResponse<SignUpResponse> response = signUpService.signUp(signUpRequest);

        assertEquals(ResponseStatusCodeConstants.SUCCESS, response.getStatusCode());
    }

    @Test
    void testSignUpValidRequest() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setFirstName("John");
        signUpRequest.setLastName("Doe");
        signUpRequest.setEmail("john@example.com");
        signUpRequest.setPassword("password");
        signUpRequest.setConfirmPassword("password");
        signUpRequest.setDob(new Date());
        signUpRequest.setMobileNumber("1234567890");

        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());

        HttpSuccessResponse<SignUpResponse> response = signUpService.signUp(signUpRequest);

        assertEquals(ResponseStatusCodeConstants.SUCCESS, response.getStatusCode());
    }

    @Test
    void testSignUpException() {
        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenThrow(new RuntimeException("Test exception"));

        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setFirstName("John");
        signUpRequest.setLastName("Doe");
        signUpRequest.setEmail("john@example.com");
        signUpRequest.setPassword("password");
        signUpRequest.setConfirmPassword("password");
        signUpRequest.setDob(new Date());
        signUpRequest.setMobileNumber("1234567890");

        assertThrows(RuntimeException.class, () -> signUpService.signUp(signUpRequest));
    }

    @Test
    void testSignUpWithExistingEmail() {
        // Arrange
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setFirstName("John");
        signUpRequest.setLastName("Doe");
        signUpRequest.setEmail("john@example.com");
        signUpRequest.setPassword("password");
        signUpRequest.setConfirmPassword("password");
        signUpRequest.setDob(new Date(System.currentTimeMillis()));
        signUpRequest.setMobileNumber("1234567890");

        UserEntity existingUser = new UserEntity();
        existingUser.setEmail("john@example.com");

        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(existingUser));

        // Act
        HttpSuccessResponse<SignUpResponse> response = signUpService.signUp(signUpRequest);

        // Assert
        assertEquals(ResponseStatusCodeConstants.BAD_REQUEST, response.getStatusCode());
    }
}
