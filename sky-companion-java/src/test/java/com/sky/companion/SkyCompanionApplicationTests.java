package com.sky.companion;

import com.sky.companion.security.jwt.JWTAuthorizationFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Spring boot testing main class
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SkyCompanionApplicationTests {

    private static final String[] ARGS = new String[]{"TEST"};
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private JWTAuthorizationFilter jwtAuthorizationFilter;

    /**
     * Test case to check {@link JWTAuthorizationFilter} exist in context and security filter chain
     */
    @Test
    void testJWTAuthorizationFilterExistInContext() {
        SecurityFilterChain filterChain = context.getBean(SecurityFilterChain.class);
        assertTrue(filterChain.getFilters().contains(jwtAuthorizationFilter));
    }

    /**
     * Test case to check that context has password encoder bean present or not
     */
    @Test
    void testPasswordEncoderBeanExistInContext() {
        PasswordEncoder bean = context.getBean(PasswordEncoder.class);
        assertTrue(bean instanceof BCryptPasswordEncoder);
    }
}
