package com.sky.companion.common.jackson;


import com.sky.companion.authentication.common.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test case class for {@link JacksonUtils} using {@link org.mockito.Mockito}
 */
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
class JacksonUtilsTest {


    private static final String INVALID_USER_JSON = "{{{}}}}}{{}{}{}";

    private static final String VALID_USER_JSON = "{\"id\":1,\"firstName\":\"Test\",\"lastName\":null,\"email\":null,\"dob\":null,\"mobileNumber\":null,\"languages\":null,\"address\":null,\"ratings\":null,\"reviews\":null,\"active\":null}";

    /**
     * test case to check valid json conversion
     */
    @Test
    void testToJsonSuccess() {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(1);
        userDTO.setFirstName("Test");
        assertEquals(VALID_USER_JSON, JacksonUtils.toJson(userDTO));
    }

    /**
     * test case to check json conversion failing
     */
    @Test
    void testToJsonFailure() {
        assertNotEquals(INVALID_USER_JSON, JacksonUtils.toJson(new UserDTO()));
    }

    /**
     * test case to check json to class conversion success
     */
    @Test
    void testFromJsonSuccess() {
        assertNotNull(JacksonUtils.fromJson(VALID_USER_JSON, UserDTO.class));
    }

    /**
     * test case to check json to class conversion failing
     */
    @Test
    void testFromJsonFailure() {
        assertNull(JacksonUtils.fromJson(INVALID_USER_JSON, UserDTO.class));
    }

}
