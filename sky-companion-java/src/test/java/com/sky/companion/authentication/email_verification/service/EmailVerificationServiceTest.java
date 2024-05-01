package com.sky.companion.authentication.email_verification.service;

import com.sky.companion.authentication.common.entity.UserEntity;
import com.sky.companion.authentication.common.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmailVerificationServiceTest {

    @InjectMocks
    private EmailVerificationServiceImpl emailVerificationService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JavaMailSender javaMailSender;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSendVerificationEmail() {
        // Arrange
        String email = "test@example.com";
        String verificationToken = UUID.randomUUID().toString();
        String verificationLink = "http://example.com/verify?token=" + verificationToken;

        // Act
        emailVerificationService.sendVerificationEmail(email, verificationLink);

        // Assert
        ArgumentCaptor<SimpleMailMessage> argumentCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        Mockito.verify(javaMailSender, Mockito.times(1)).send(argumentCaptor.capture());

        SimpleMailMessage capturedMessage = argumentCaptor.getValue();

        assertEquals(email, capturedMessage.getTo()[0]);
    }

    @Test
    void testVerifyEmailSuccess() {
        // Arrange
        String verificationToken = UUID.randomUUID().toString();
        UserEntity user = new UserEntity();
        user.setVerificationToken(verificationToken);

        // Act
        Mockito.when(userRepository.findByVerificationToken(verificationToken)).thenReturn(user);
        String result = emailVerificationService.verifyEmail(verificationToken);

        // Assert
        assertNull(user.getVerificationToken());
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    void testVerifyEmailInvalidToken() {
        // Arrange
        String invalidToken = "invalid-token";

        // Act
        Mockito.when(userRepository.findByVerificationToken(invalidToken)).thenReturn(null);
        String result = emailVerificationService.verifyEmail(invalidToken);

        // Assert
        assertTrue(result.contains("Invalid verification token"));
        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any(UserEntity.class));
    }

    @Test
    void testVerifyEmailException() {
        // Arrange
        String verificationToken = UUID.randomUUID().toString();

        // Act
        Mockito.when(userRepository.findByVerificationToken(verificationToken)).thenThrow(new RuntimeException("Test exception"));
        assertThrows(RuntimeException.class, () -> emailVerificationService.verifyEmail(verificationToken));
    }
}
