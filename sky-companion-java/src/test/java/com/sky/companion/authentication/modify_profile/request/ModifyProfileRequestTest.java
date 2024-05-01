package com.sky.companion.authentication.modify_profile.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ModifyProfileRequestTest {
    final private List<Integer> languageList = Arrays.asList(1, 2, 3);
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testNoArgsConstructor() {
        ModifyProfileRequest modifyProfileRequest = new ModifyProfileRequest();
        assertNull(modifyProfileRequest.getFirstname());
    }

    @Test
    void testFirstNameGetterAndSetter() {
        ModifyProfileRequest modifyProfileRequest = new ModifyProfileRequest();
        modifyProfileRequest.setFirstname("John");
        assertEquals("John", modifyProfileRequest.getFirstname());
    }

    @Test
    void testLastNameGetterAndSetter() {
        ModifyProfileRequest modifyProfileRequest = new ModifyProfileRequest();
        modifyProfileRequest.setLastname("Doe");
        assertEquals("Doe", modifyProfileRequest.getLastname());
    }

    @Test
    void testDobGetterAndSetter() {
        ModifyProfileRequest modifyProfileRequest = new ModifyProfileRequest();
        Date dob = new Date();
        modifyProfileRequest.setDob(dob);
        assertEquals(dob, modifyProfileRequest.getDob());
    }

    @Test
    void testMobileNumberGetterAndSetter() {
        ModifyProfileRequest modifyProfileRequest = new ModifyProfileRequest();
        modifyProfileRequest.setMobileNumber("1234567890");
        assertEquals("1234567890", modifyProfileRequest.getMobileNumber());
    }

    @Test
    void testAddressLine1GetterAndSetter() {
        ModifyProfileRequest modifyProfileRequest = new ModifyProfileRequest();
        modifyProfileRequest.setAddressLine1("Address1");
        assertEquals("Address1", modifyProfileRequest.getAddressLine1());
    }

    @Test
    void testAddressLine2GetterAndSetter() {
        ModifyProfileRequest modifyProfileRequest = new ModifyProfileRequest();
        modifyProfileRequest.setAddressLine2("Address2");
        assertEquals("Address2", modifyProfileRequest.getAddressLine2());
    }

    @Test
    void testPostalCodeGetterAndSetter() {
        ModifyProfileRequest modifyProfileRequest = new ModifyProfileRequest();
        modifyProfileRequest.setPostalCode("12345");
        assertEquals("12345", modifyProfileRequest.getPostalCode());
    }

    @Test
    void testCityGetterAndSetter() {
        ModifyProfileRequest modifyProfileRequest = new ModifyProfileRequest();
        modifyProfileRequest.setCity("City");
        assertEquals("City", modifyProfileRequest.getCity());
    }

    @Test
    void testProvinceGetterAndSetter() {
        ModifyProfileRequest modifyProfileRequest = new ModifyProfileRequest();
        modifyProfileRequest.setProvince("Province");
        assertEquals("Province", modifyProfileRequest.getProvince());
    }

    @Test
    void testCountryGetterAndSetter() {
        ModifyProfileRequest modifyProfileRequest = new ModifyProfileRequest();
        modifyProfileRequest.setCountry("Country");
        assertEquals("Country", modifyProfileRequest.getCountry());
    }

    @Test
    void testLanguagesGetterAndSetter() {
        ModifyProfileRequest modifyProfileRequest = new ModifyProfileRequest();
        List<Integer> languages = languageList;
        modifyProfileRequest.setLanguages(languages);
        assertEquals(languages, modifyProfileRequest.getLanguages());
    }

    @Test
    void testValidModifyProfileRequest() {
        Date dob = new Date();
        List<Integer> languages = languageList;
        ModifyProfileRequest modifyProfileRequest = new ModifyProfileRequest();
        modifyProfileRequest.setFirstname("John");
        modifyProfileRequest.setLastname("Doe");
        modifyProfileRequest.setDob(dob);
        modifyProfileRequest.setMobileNumber("1234567890");
        modifyProfileRequest.setAddressLine1("address1");
        modifyProfileRequest.setAddressLine2("address2");
        modifyProfileRequest.setPostalCode("12345");
        modifyProfileRequest.setCity("city");
        modifyProfileRequest.setProvince("province");
        modifyProfileRequest.setCountry("country");
        modifyProfileRequest.setLanguages(languages);

        Set<ConstraintViolation<ModifyProfileRequest>> violations = validator.validate(modifyProfileRequest);

        assertTrue(violations.isEmpty());
    }

    @Test
    void testEmptyFirstName() {
        ModifyProfileRequest modifyProfileRequest = new ModifyProfileRequest();
        modifyProfileRequest.setFirstname("John");
        modifyProfileRequest.setLastname("Doe");
        modifyProfileRequest.setDob(new Date());
        modifyProfileRequest.setMobileNumber("1234567890");
        modifyProfileRequest.setAddressLine1("address1");
        modifyProfileRequest.setAddressLine2("address2");
        modifyProfileRequest.setPostalCode("12345");
        modifyProfileRequest.setCity("city");
        modifyProfileRequest.setProvince("province");
        modifyProfileRequest.setCountry("country");
        modifyProfileRequest.setLanguages(languageList);

        Set<ConstraintViolation<ModifyProfileRequest>> violations = validator.validate(modifyProfileRequest);

        for (ConstraintViolation<ModifyProfileRequest> violation : violations) {
            assertTrue(violation.getMessage().startsWith("First name"));
        }
    }


}
