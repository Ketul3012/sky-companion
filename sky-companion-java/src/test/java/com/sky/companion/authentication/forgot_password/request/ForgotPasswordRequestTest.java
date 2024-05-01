package com.sky.companion.authentication.forgot_password.request;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for forgot password request
 */
class ForgotPasswordRequestTest {

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
     * test valid forgot password request
     */
    @Test
    void testValidForgotRequest() {
        // Arrange
        ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest();
        forgotPasswordRequest.setEmail("email@test.com");

        // Act
        Set<ConstraintViolation<ForgotPasswordRequest>> violations = validator.validate(forgotPasswordRequest);

        // Assert
        assertTrue(violations.isEmpty());
    }

    /**
     * test invalid email format
     */
    @Test
    void testInvalidEmailFormat() {
        // Arrange
        ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest();
        forgotPasswordRequest.setEmail("invalid-email");

        // Act
        Set<ConstraintViolation<ForgotPasswordRequest>> violations = validator.validate(forgotPasswordRequest);

        // Assert
        ConstraintViolation<ForgotPasswordRequest> violation = violations.iterator().next();
        assertEquals("email should be in proper format", violation.getMessage());
    }

    /**
     * test empty email address
     */
    @Test
    void testEmptyEmail() {
        // Arrange
        ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest();
        forgotPasswordRequest.setEmail("");

        // Act
        Set<ConstraintViolation<ForgotPasswordRequest>> violations = validator.validate(forgotPasswordRequest);

        // Assert
        ConstraintViolation<ForgotPasswordRequest> violation = violations.iterator().next();
        assertEquals("email address cannot be empty", violation.getMessage());
    }

    /**
     * test cases that email setting works successfully
     */
    @Test
    void testEmailSettingSuccess() {
        // Arrange
        ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest();
        forgotPasswordRequest.setEmail("test@test.com");

        // Act and Assert
        assertNotNull(forgotPasswordRequest.getEmail());
    }
}
