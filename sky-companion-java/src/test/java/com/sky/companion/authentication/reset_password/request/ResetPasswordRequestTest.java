package com.sky.companion.authentication.reset_password.request;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for forgot password request
 */
class ResetPasswordRequestTest {

    private static Validator validator;

    /**
     * setup validator
     */
    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * test valid reset password request
     */
    @Test
    void testValidForgotRequest() {
        // Arrange
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        resetPasswordRequest.setToken(UUID.randomUUID().toString());
        resetPasswordRequest.setNewPassword(UUID.randomUUID().toString());

        // Act
        Set<ConstraintViolation<ResetPasswordRequest>> violations = validator.validate(resetPasswordRequest);

        // Assert
        assertTrue(violations.isEmpty());
    }

    /**
     * test empty token in request
     */
    @Test
    void testEmptyTokenRequest() {
        // Arrange
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        resetPasswordRequest.setNewPassword(UUID.randomUUID().toString());

        // Act
        Set<ConstraintViolation<ResetPasswordRequest>> violations = validator.validate(resetPasswordRequest);

        // Assert
        ConstraintViolation<ResetPasswordRequest> violation = violations.iterator().next();
        assertEquals("token cannot be empty", violation.getMessage());
    }

    /**
     * test empty token in request
     */
    @Test
    void testEmptyPasswordRequest() {
        // Arrange
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        resetPasswordRequest.setToken(UUID.randomUUID().toString());

        // Act
        Set<ConstraintViolation<ResetPasswordRequest>> violations = validator.validate(resetPasswordRequest);

        // Assert
        ConstraintViolation<ResetPasswordRequest> violation = violations.iterator().next();
        assertEquals("password cannot be empty", violation.getMessage());
    }

    /**
     * test cases that email setting works successfully
     */
    @Test
    void testResetPasswordRequestSettingSuccess() {
        // Arrange
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        resetPasswordRequest.setToken(UUID.randomUUID().toString());
        resetPasswordRequest.setNewPassword(UUID.randomUUID().toString());

        // Act and Assert
        assertNotNull(resetPasswordRequest.getToken());
    }
}
