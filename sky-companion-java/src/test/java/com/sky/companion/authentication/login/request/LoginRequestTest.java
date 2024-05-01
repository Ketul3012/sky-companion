package com.sky.companion.authentication.login.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LoginRequestTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testNoArgsConstructor() {
        LoginRequest loginRequest = new LoginRequest();
        assertNull(loginRequest.getPassword());
    }

    @Test
    void testEmailGetterAndSetter() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("abc@def.com");
        assertEquals("abc@def.com", loginRequest.getEmail());
    }

    @Test
    void testPasswordGetterAndSetter() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("abc!!!123");
        assertEquals("abc!!!123", loginRequest.getPassword());
    }

    @Test
    void testEmailNullValue() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(null);
        assertNull(loginRequest.getEmail());
    }

    @Test
    void testPasswordNullValue() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword(null);
        assertNull(loginRequest.getPassword());
    }

    @Test
    void testValidLoginRequest() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password");

        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(loginRequest);

        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidEmailFormat() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("invalid-email");
        loginRequest.setPassword("password");

        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(loginRequest);

        ConstraintViolation<LoginRequest> violation = violations.iterator().next();
        assertEquals("email should be in proper format", violation.getMessage());
    }

    @Test
    void testEmptyEmail() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("");
        loginRequest.setPassword("password");

        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(loginRequest);

        ConstraintViolation<LoginRequest> violation = violations.iterator().next();
        assertEquals("email address cannot be empty", violation.getMessage());
    }

    @Test
    void testEmptyPassword() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("email@gmail.com");
        loginRequest.setPassword("");

        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(loginRequest);

        ConstraintViolation<LoginRequest> violation = violations.iterator().next();
        assertEquals("password cannot be empty", violation.getMessage());
    }

}