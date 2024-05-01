package com.sky.companion.authentication.forgot_password.controller;

import com.sky.companion.authentication.forgot_password.request.ForgotPasswordRequest;
import com.sky.companion.authentication.forgot_password.service.ForgotPasswordService;
import com.sky.companion.common.constants.ResponseMessagesConstants;
import com.sky.companion.common.constants.ResponseStatusCodeConstants;
import com.sky.companion.common.model.HttpSuccessResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Test case class for {@link ForgotPasswordController} using {@link org.mockito.Mockito}
 */
@ExtendWith(MockitoExtension.class)
class ForgotPasswordControllerTest {

    @Mock
    private ForgotPasswordService forgotPasswordService;

    @InjectMocks
    private ForgotPasswordController forgotPasswordController;

    private MockMvc mockMvc;

    /**
     * Test case for forgot password endpoint
     *
     * @throws Exception
     */
    @Test
    void testForgotPasswordEndpoint() throws Exception {
        // Arrange
        ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest();
        forgotPasswordRequest.setEmail("test@example.com");

        HttpSuccessResponse<String> successResponse = new HttpSuccessResponse<>(
                ResponseStatusCodeConstants.SUCCESS,
                ResponseMessagesConstants.SUCCESS,
                "Email sent successfully"
        );

        when(forgotPasswordService.forgotPassword(any(ForgotPasswordRequest.class))).thenReturn(successResponse);

        // Act
        HttpSuccessResponse<String> response = forgotPasswordController.forgotPassword(forgotPasswordRequest);

        // Assert
        assertEquals(ResponseStatusCodeConstants.SUCCESS, response.getStatusCode());
    }
}
