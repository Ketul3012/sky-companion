package com.sky.companion.authentication.login.controller;

import com.sky.companion.authentication.login.request.LoginRequest;
import com.sky.companion.authentication.login.response.LoginResponse;
import com.sky.companion.authentication.login.service.LoginService;
import com.sky.companion.common.constants.ResponseMessagesConstants;
import com.sky.companion.common.constants.ResponseStatusCodeConstants;
import com.sky.companion.common.model.HttpSuccessResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test case class for {@link LoginController} using {@link Mockito}
 */
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
class LoginControllerTest {
    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginController loginController;


    @Test
    void testLoginController() throws Exception {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@test.com");
        loginRequest.setPassword("password");

        HttpSuccessResponse<LoginResponse> expectedLoginResponse = new HttpSuccessResponse<>(ResponseStatusCodeConstants.SUCCESS, ResponseMessagesConstants.SUCCESS, new LoginResponse());
        Mockito.when(loginController.login(loginRequest)).thenReturn(expectedLoginResponse);

        // Act & Assert
        HttpSuccessResponse<LoginResponse> response = loginController.login(loginRequest);
        assertEquals(ResponseStatusCodeConstants.SUCCESS, response.getStatusCode());

    }
}