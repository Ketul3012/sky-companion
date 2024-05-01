package com.sky.companion.authentication.reset_password.controller;

import com.sky.companion.authentication.reset_password.request.ResetPasswordRequest;
import com.sky.companion.authentication.reset_password.service.ResetPasswordService;
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
 * Test case class for {@link ResetPasswordController} using {@link org.mockito.Mockito}
 */
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
class ResetPasswordControllerTest {

    @Mock
    private ResetPasswordService resetPasswordService;

    @InjectMocks
    private ResetPasswordController resetPasswordController;

    /**
     * Test case for reset password endpoint
     *
     * @throws Exception
     */
    @Test
    void testResetPasswordEndpoint() {
        // Arrange
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        resetPasswordRequest.setToken("test-token");
        resetPasswordRequest.setNewPassword("new-password");

        HttpSuccessResponse<String> successResponse = new HttpSuccessResponse<>(
                ResponseStatusCodeConstants.SUCCESS,
                ResponseMessagesConstants.SUCCESS,
                "Password changed successfully, you can proceed with login"
        );

        Mockito.when(resetPasswordService.resetPassword(resetPasswordRequest)).thenReturn(successResponse);

        // Act
        HttpSuccessResponse<String> response = resetPasswordController.resetPassword(resetPasswordRequest);

        // Assert
        assertEquals(ResponseStatusCodeConstants.SUCCESS, response.getStatusCode());
    }
}
