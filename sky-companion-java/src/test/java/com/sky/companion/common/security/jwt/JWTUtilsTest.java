package com.sky.companion.common.security.jwt;

import com.sky.companion.authentication.common.entity.UserEntity;
import com.sky.companion.security.jwt.JWTUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Test case class for {@link com.sky.companion.security.jwt.JWTUtils} using {@link Mockito}
 */
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
class JWTUtilsTest {
    private static final String JWT_TOKEN = "Bearer actualToken";
    private static final UserEntity USER_ENTITY = new UserEntity();
    @InjectMocks
    private static JWTUtils jwtUtils;

    /**
     * this method run before all test cases to setup required parameters for test cases
     */
    @BeforeAll
    static void setUp() {
        USER_ENTITY.setId(1);
        USER_ENTITY.setFirstName("test");
        jwtUtils = new JWTUtils();
    }

    /**
     * This method run the unit test case for {@link JWTUtils} for validation failed case
     */
    @Test
    void testJWTValidationFailed() {
        // Arrange and Act
        Integer userId = jwtUtils.validateJWTTokenAndGetUserId(JWT_TOKEN);

        // Assert
        assertNull(userId);
    }


}
