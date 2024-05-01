package com.sky.companion.common.security.jwt;

import com.sky.companion.authentication.common.dto.UserDTO;
import com.sky.companion.authentication.get_user.service.GetUserService;
import com.sky.companion.common.constants.ResponseMessagesConstants;
import com.sky.companion.common.constants.ResponseStatusCodeConstants;
import com.sky.companion.common.exceptions.UserNotFoundException;
import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.security.jwt.JWTAuthorizationFilter;
import com.sky.companion.security.jwt.JWTConstants;
import com.sky.companion.security.jwt.JWTUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Test case class for {@link JWTAuthorizationFilterTest} using {@link Mockito}
 */
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
class JWTAuthorizationFilterTest {
    private final int successStatusCode = 200;
    private final int forbiddenErrorStatusCode = 403;
    private final int value = 123;


    @InjectMocks
    private JWTAuthorizationFilter jwtAuthorizationFilter;

    @Mock
    private GetUserService getUserService;

    @Mock
    private JWTUtils jwtUtils;

    /**
     * This test case check that filter should not happen for login endpoint
     */
    @Test
    void testShouldNotFilterLogin() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod(HttpMethod.POST.name());
        request.setPathInfo("/login");
        assertTrue(jwtAuthorizationFilter.shouldNotFilter(request));
    }

    /**
     * This test case check that filter should not happen for signup endpoint
     */
    @Test
    void testShouldNotFilterSignup() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod(HttpMethod.POST.name());
        request.setPathInfo("/signup");
        assertTrue(jwtAuthorizationFilter.shouldNotFilter(request));
    }

    /**
     * This test case check that filter should not happen for forgot-password endpoint
     */
    @Test
    void testShouldNotFilterForgotPassword() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod(HttpMethod.POST.name());
        request.setPathInfo("/forgot-password");
        assertTrue(jwtAuthorizationFilter.shouldNotFilter(request));
    }

    /**
     * This test case check that filter should not happen for reset-password endpoint
     */
    @Test
    void testShouldNotFilterResetPassword() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod(HttpMethod.POST.name());
        request.setPathInfo("/reset-password");
        assertTrue(jwtAuthorizationFilter.shouldNotFilter(request));
    }

    /**
     * This test case check that filter should not happen for verify endpoint
     */
    @Test
    void testShouldNotFilterVerify() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod(HttpMethod.GET.name());
        request.setPathInfo("/verify");
        assertTrue(jwtAuthorizationFilter.shouldNotFilter(request));
    }


    /**
     * This test case check that filter should happen for any other endpoint
     */
    @Test
    void testShouldFilter() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod(HttpMethod.POST.name());

        assertFalse(jwtAuthorizationFilter.shouldNotFilter(request));
    }

    /**
     * this test case run do filter internal method from {@link JWTAuthorizationFilter} for valid token case
     *
     * @throws ServletException
     * @throws IOException
     * @throws UserNotFoundException
     */
    @Test
    void testDoFilterInternalValidToken() throws ServletException, IOException, UserNotFoundException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod(HttpMethod.GET.name());
        request.addHeader(HttpHeaders.AUTHORIZATION, JWTConstants.PREFIX + "validToken");

        MockHttpServletResponse response = new MockHttpServletResponse();

        when(jwtUtils.validateJWTTokenAndGetUserId(any())).thenReturn(value);
        when(getUserService.getUserById(anyInt())).thenReturn(new HttpSuccessResponse<>(ResponseStatusCodeConstants.SUCCESS, ResponseMessagesConstants.SUCCESS,
                new UserDTO()));

        jwtAuthorizationFilter.doFilterInternal(request, response, new MockFilterChain());

        assertEquals(successStatusCode, response.getStatus());
        verify(getUserService, times(1)).getUserById(value);
        verify(jwtUtils, times(1)).validateJWTTokenAndGetUserId(JWTConstants.PREFIX + "validToken");
    }

    /**
     * this test case run do filter internal method from {@link JWTAuthorizationFilter} for invalid token case
     *
     * @throws ServletException
     * @throws IOException
     * @throws UserNotFoundException
     */
    @Test
    void testDoFilterInternalInvalidToken() throws ServletException, IOException, UserNotFoundException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod(HttpMethod.GET.name());
        request.addHeader(HttpHeaders.AUTHORIZATION, "invalidToken");

        MockHttpServletResponse response = new MockHttpServletResponse();

        jwtAuthorizationFilter.doFilterInternal(request, response, new MockFilterChain());

        assertEquals(forbiddenErrorStatusCode, response.getStatus());
        verify(getUserService, never()).getUserById(anyInt());
    }


    /**
     * this test case run do filter internal method from {@link JWTAuthorizationFilter} for valid token but user not found case
     *
     * @throws ServletException
     * @throws IOException
     * @throws UserNotFoundException
     */
    @Test
    void testDoFilterInternalUserNotFound() throws ServletException, IOException, UserNotFoundException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod(HttpMethod.GET.name());
        request.addHeader(HttpHeaders.AUTHORIZATION, JWTConstants.PREFIX + "validToken");

        MockHttpServletResponse response = new MockHttpServletResponse();

        when(jwtUtils.validateJWTTokenAndGetUserId(any())).thenReturn(value);
        when(getUserService.getUserById(anyInt())).thenThrow(new UserNotFoundException());

        jwtAuthorizationFilter.doFilterInternal(request, response, new MockFilterChain());

        assertEquals(forbiddenErrorStatusCode, response.getStatus());
        verify(getUserService, times(1)).getUserById(value);
    }

    /**
     * this test case validate writeForbiddenResponse method
     *
     * @throws ServletException
     * @throws IOException
     * @throws UserNotFoundException
     */
    @Test
    void testWriteForbiddenResponse() throws IOException {
        MockHttpServletResponse response = new MockHttpServletResponse();

        jwtAuthorizationFilter.writeForbiddenResponse(response);

        assertEquals(forbiddenErrorStatusCode, response.getStatus());
    }
}