package com.sky.companion.authentication.reset_password.service;

import com.sky.companion.authentication.common.entity.UserEntity;
import com.sky.companion.authentication.common.repository.UserRepository;
import com.sky.companion.authentication.reset_password.request.ResetPasswordRequest;
import com.sky.companion.common.model.HttpSuccessResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Test case class for {@link ResetPasswordService} using {@link Mockito}
 */
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
class ResetPasswordServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ResetPasswordServiceImpl resetPasswordService;

    /**
     * test case for reset password success case
     */
    @Test
    void testResetPasswordSuccess() {
        // Arrange
        ResetPasswordRequest request = new ResetPasswordRequest();
        request.setToken(UUID.randomUUID().toString());
        request.setNewPassword(UUID.randomUUID().toString());

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@example.com");
        when(userRepository.findByResetPasswordToken(request.getToken())).thenReturn(userEntity);
        when(passwordEncoder.encode(request.getNewPassword())).thenReturn(UUID.randomUUID().toString());

        // Act
        HttpSuccessResponse<String> response = resetPasswordService.resetPassword(request);

        // Assert
        verify(passwordEncoder).encode(request.getNewPassword());
        verify(userRepository).findByResetPasswordToken(request.getToken());
        verify(userRepository).save(userEntity);
        assertEquals("Password changed successfully, you can proceed with login", response.getData());

    }

    /**
     * test case for forgot password invalid email address
     */
    @Test
    void testResetPasswordInvalidToken() {

        // Arrange
        ResetPasswordRequest request = new ResetPasswordRequest();
        request.setToken("invalid token");
        when(userRepository.findByResetPasswordToken(request.getToken())).thenReturn(null);

        // Act
        HttpSuccessResponse<String> response = resetPasswordService.resetPassword(request);

        // Assert
        verify(userRepository).findByResetPasswordToken(request.getToken());
        verify(userRepository, never()).save(any(UserEntity.class));
        assertEquals("Invalid reset password token", response.getData());
    }
}
