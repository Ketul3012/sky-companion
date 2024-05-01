package com.sky.companion.authentication.forgot_password.service;

import com.sky.companion.authentication.common.entity.UserEntity;
import com.sky.companion.authentication.common.repository.UserRepository;
import com.sky.companion.authentication.forgot_password.request.ForgotPasswordRequest;
import com.sky.companion.common.model.HttpSuccessResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Test case class for {@link ForgotPasswordService} using {@link Mockito}
 */
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
class ForgotPasswordServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private ForgotPasswordServiceImpl forgotPasswordService;

    /**
     * test case for forgot password success case
     */
    @Test
    void testForgotPasswordSuccess() {
        // Arrange
        ForgotPasswordRequest request = new ForgotPasswordRequest("test@example.com");
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@example.com");
        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(userEntity));

        // Act
        HttpSuccessResponse<String> response = forgotPasswordService.forgotPassword(request);

        // Assert
        verify(userRepository).findByEmail(request.getEmail());
        verify(userRepository).save(userEntity);
        verify(javaMailSender).send(any(SimpleMailMessage.class));
        assertEquals("Email sent successfully", response.getData());

    }

    /**
     * test case for forgot password invalid email address
     */
    @Test
    void testForgotPasswordInvalidEmailAddress() {

        // Arrange
        ForgotPasswordRequest request = new ForgotPasswordRequest("nonexistent@example.com");
        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());

        // Act
        HttpSuccessResponse<String> response = forgotPasswordService.forgotPassword(request);

        // Assert
        verify(userRepository).findByEmail(request.getEmail());
        verify(userRepository, never()).save(any(UserEntity.class));
        verify(javaMailSender, never()).send(any(SimpleMailMessage.class));
        assertEquals("Invalid email address", response.getData());
    }


    /**
     * test case for forgot password unable to send email
     */
    @Test
    void testForgotPasswordUnableToSendEmail() {

        // Arrange
        ForgotPasswordRequest request = new ForgotPasswordRequest("test@example.com");
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@example.com");
        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(userEntity));
        doThrow(new RuntimeException("Unable to send email")).when(javaMailSender).send(mock(SimpleMailMessage.class));

        // Act
        HttpSuccessResponse<String> response = forgotPasswordService.forgotPassword(request);

        // Assert
        verify(userRepository).findByEmail(request.getEmail());
        verify(userRepository).save(userEntity);
        assertEquals("System not able to send email", response.getData());

    }

}
