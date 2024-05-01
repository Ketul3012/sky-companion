package com.sky.companion.authentication.signup.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SignUpResponseTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testNoArgsConstructor() {
        SignUpResponse signUpResponse = new SignUpResponse();
        assertNull(signUpResponse.getId());
    }

    @Test
    void testIdGetterAndSetter() {
        SignUpResponse signUpResponse = new SignUpResponse();
        signUpResponse.setId(1);
        assertEquals(1, signUpResponse.getId());
    }

    @Test
    void testFirstNameGetterAndSetter() {
        SignUpResponse signUpResponse = new SignUpResponse();
        signUpResponse.setFirstName("John");
        assertEquals("John", signUpResponse.getFirstName());
    }

    @Test
    void testLastNameGetterAndSetter() {
        SignUpResponse signUpResponse = new SignUpResponse();
        signUpResponse.setLastName("Doe");
        assertEquals("Doe", signUpResponse.getLastName());
    }

    @Test
    void testIdNullValue() {
        SignUpResponse signUpResponse = new SignUpResponse();
        signUpResponse.setId(null);
        assertNull(signUpResponse.getId());
    }

    @Test
    void testFirstNameNullValue() {
        SignUpResponse signUpResponse = new SignUpResponse();
        signUpResponse.setFirstName(null);
        assertNull(signUpResponse.getFirstName());
    }

    @Test
    void testLastNameNullValue() {
        SignUpResponse signUpResponse = new SignUpResponse();
        signUpResponse.setLastName(null);
        assertNull(signUpResponse.getLastName());
    }
}
