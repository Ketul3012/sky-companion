package com.sky.companion.authentication.signup.controller;

import com.sky.companion.authentication.signup.request.SignUpRequest;
import com.sky.companion.authentication.signup.response.SignUpResponse;
import com.sky.companion.authentication.signup.service.SignUpService;
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
 * Test case class for {@link SignUpController} using {@link org.mockito.Mockito}
 */
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
class SignUpControllerTest {

    @Mock
    private SignUpService signUpService;

    @InjectMocks
    private SignUpController signUpController;

    /**
     * Test case for signup endpoint
     *
     * @throws Exception
     */
    @Test
    void testSignUpEndpoint() {
        // Arrange
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setFirstName("John");
        signUpRequest.setLastName("Doe");
        signUpRequest.setEmail("john.doe@example.com");
        signUpRequest.setPassword("password");
        signUpRequest.setConfirmPassword("password");

        SignUpResponse signUpResponse = new SignUpResponse();
        signUpResponse.setId(1);
        signUpResponse.setFirstName("John");
        signUpResponse.setLastName("Doe");
        HttpSuccessResponse<SignUpResponse> signUpHttpSuccessResponse = new HttpSuccessResponse<>(
                ResponseStatusCodeConstants.SUCCESS,
                ResponseMessagesConstants.SUCCESS,
                signUpResponse
        );

        Mockito.when(signUpController.signup(signUpRequest)).thenReturn(signUpHttpSuccessResponse);

        // Act
        HttpSuccessResponse<SignUpResponse> response = signUpController.signup(signUpRequest);

        // Assert
        assertEquals(ResponseStatusCodeConstants.SUCCESS, response.getStatusCode());
    }
}
