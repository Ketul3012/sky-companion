package com.sky.companion.authentication.signup.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SignUpRequestTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testNoArgsConstructor() {
        SignUpRequest signUpRequest = new SignUpRequest();
        assertNull(signUpRequest.getFirstName());
    }

    @Test
    void testFirstNameGetterAndSetter() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setFirstName("John");
        assertEquals("John", signUpRequest.getFirstName());
    }

    @Test
    void testLastNameGetterAndSetter() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setLastName("Doe");
        assertEquals("Doe", signUpRequest.getLastName());
    }

    @Test
    void testEmailGetterAndSetter() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setEmail("john@example.com");
        assertEquals("john@example.com", signUpRequest.getEmail());
    }

    @Test
    void testPasswordGetterAndSetter() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setPassword("password123");
        assertEquals("password123", signUpRequest.getPassword());
    }

    @Test
    void testIsActiveGetterAndSetter() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setActive(true);
        assertTrue(signUpRequest.getActive());
    }

    @Test
    void testConfirmPasswordGetterAndSetter() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setConfirmPassword("password123");
        assertEquals("password123", signUpRequest.getConfirmPassword());
    }

    @Test
    void testDobGetterAndSetter() {
        SignUpRequest signUpRequest = new SignUpRequest();
        Date dob = new Date();
        signUpRequest.setDob(dob);
        assertEquals(dob, signUpRequest.getDob());
    }

    @Test
    void testMobileNumberGetterAndSetter() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setMobileNumber("1234567890");
        assertEquals("1234567890", signUpRequest.getMobileNumber());
    }

    @Test
    void testValidSignUpRequest() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setFirstName("John");
        signUpRequest.setLastName("Doe");
        signUpRequest.setEmail("john@example.com");
        signUpRequest.setPassword("password");
        signUpRequest.setConfirmPassword("password");
        signUpRequest.setActive(true);
        signUpRequest.setDob(new Date());
        signUpRequest.setMobileNumber("1234567890");

        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(signUpRequest);

        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidEmailFormat() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setFirstName("John");
        signUpRequest.setLastName("Doe");
        signUpRequest.setEmail("invalid - email");
        signUpRequest.setPassword("password");
        signUpRequest.setConfirmPassword("password");
        signUpRequest.setActive(true);
        signUpRequest.setDob(new Date());
        signUpRequest.setMobileNumber("1234567890");

        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(signUpRequest);

        ConstraintViolation<SignUpRequest> violation = violations.iterator().next();
        assertEquals("Email should be in proper format", violation.getMessage());
    }

    @Test
    void testEmptyFirstName() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setFirstName("");
        signUpRequest.setLastName("Doe");
        signUpRequest.setEmail("john@example.com");
        signUpRequest.setPassword("password");
        signUpRequest.setConfirmPassword("password");
        signUpRequest.setActive(true);
        signUpRequest.setDob(new Date());
        signUpRequest.setMobileNumber("1234567890");

        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(signUpRequest);

        // You can also assert the specific messages if needed
        for (ConstraintViolation<SignUpRequest> violation : violations) {
            assertTrue(violation.getMessage().startsWith("First Name"));
        }
    }

    @Test
    void testEmptyLastName() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setFirstName("John");
        signUpRequest.setLastName("");
        signUpRequest.setEmail("john@example.com");
        signUpRequest.setPassword("password");
        signUpRequest.setConfirmPassword("password");
        signUpRequest.setActive(true);
        signUpRequest.setDob(new Date());
        signUpRequest.setMobileNumber("1234567890");

        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(signUpRequest);

        for (ConstraintViolation<SignUpRequest> violation : violations) {
            assertTrue(violation.getMessage().startsWith("Last Name"));
        }
    }

    @Test
    void testEmptyEmail() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setFirstName("John");
        signUpRequest.setLastName("Doe");
        signUpRequest.setEmail("");
        signUpRequest.setPassword("password");
        signUpRequest.setConfirmPassword("password");
        signUpRequest.setActive(true);
        signUpRequest.setDob(new Date());
        signUpRequest.setMobileNumber("1234567890");

        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(signUpRequest);

        for (ConstraintViolation<SignUpRequest> violation : violations) {
            assertTrue(violation.getMessage().startsWith("Email"));
        }
    }

    @Test
    void testEmptyPassword() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setFirstName("John");
        signUpRequest.setLastName("Doe");
        signUpRequest.setEmail("john@example.com");
        signUpRequest.setPassword("");
        signUpRequest.setConfirmPassword("password");
        signUpRequest.setActive(true);
        signUpRequest.setDob(new Date());
        signUpRequest.setMobileNumber("1234567890");

        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(signUpRequest);

        for (ConstraintViolation<SignUpRequest> violation : violations) {
            assertTrue(violation.getMessage().startsWith("Password"));
        }
    }

    @Test
    void testEmptyDateOfBirth() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setFirstName("John");
        signUpRequest.setLastName("Doe");
        signUpRequest.setEmail("john@example.com");
        signUpRequest.setPassword("password");
        signUpRequest.setConfirmPassword("password");
        signUpRequest.setActive(true);
        signUpRequest.setDob(null);
        signUpRequest.setMobileNumber("1234567890");
        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(signUpRequest);

        ConstraintViolation<SignUpRequest> violation = violations.iterator().next();
        assertEquals("Date of Birth cant be empty", violation.getMessage());
    }

    @Test
    void testEmptyMobileNumber() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setFirstName("John");
        signUpRequest.setLastName("Doe");
        signUpRequest.setEmail("john@example.com");
        signUpRequest.setPassword("password");
        signUpRequest.setConfirmPassword("password");
        signUpRequest.setActive(true);
        signUpRequest.setDob(new Date());
        signUpRequest.setMobileNumber("");

        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(signUpRequest);

        ConstraintViolation<SignUpRequest> violation = violations.iterator().next();
        assertEquals("Mobile Number cannot be empty", violation.getMessage());
    }
}
