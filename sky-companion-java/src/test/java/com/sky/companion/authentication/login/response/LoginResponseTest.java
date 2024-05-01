package com.sky.companion.authentication.login.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.*;

class LoginResponseTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testNoArgsConstructor() {
        LoginResponse loginResponse = new LoginResponse();
        assertNull(loginResponse.getId());
    }

    @Test
    void testIdGetterAndSetter() {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setId(1);
        assertEquals(1, loginResponse.getId());
    }

    @Test
    void testFirstNameGetterAndSetter() {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setFirstName("abc");
        assertEquals("abc", loginResponse.getFirstName());
    }

    @Test
    void testLastNameGetterAndSetter() {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setLastName("abc");
        assertEquals("abc", loginResponse.getLastName());
    }

    @Test
    void testEmailGetterAndSetter() {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setEmail("abc@def.com");
        assertEquals("abc@def.com", loginResponse.getEmail());
    }

    @Test
    void testJwtTokenGetterAndSetter() {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setJwtToken("jwtToken");
        assertEquals("jwtToken", loginResponse.getJwtToken());
    }

    @Test
    void testIdNullValue() {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setId(null);
        assertNull(loginResponse.getId());
    }

    @Test
    void testFirstNameNullValue() {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setFirstName(null);
        assertNull(loginResponse.getFirstName());
    }

    @Test
    void testLastNameNullValue() {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setLastName(null);
        assertNull(loginResponse.getLastName());
    }

    @Test
    void testEmailNullValue() {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setEmail(null);
        assertNull(loginResponse.getEmail());
    }

    @Test
    void testJwtTokenNullValue() {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setJwtToken(null);
        assertNull(loginResponse.getJwtToken());
    }

    @Test
    void testEqualsWithDifferentObjectType() {
        LoginResponse loginResponse = new LoginResponse();
        assertNotEquals("Not a LoginResponse", loginResponse);
    }
}
