/**
 * This class represents the controller for the forgot password functionality.
 * It handles the HTTP requests related to forgot password feature.
 * The controller receives a request with an email address and passes it to the ForgotPasswordService.
 * It returns a response indicating whether the email was sent successfully or if the user email address is invalid.
 * This controller is an important part of the project as it provides the endpoint for the forgot password feature.
 */

package com.sky.companion.authentication.forgot_password.controller;

import com.sky.companion.authentication.forgot_password.request.ForgotPasswordRequest;
import com.sky.companion.authentication.forgot_password.service.ForgotPasswordService;
import com.sky.companion.common.model.HttpSuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ForgotPasswordController {

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    /**
     * method to declare forgot password endpoint created using {@link PostMapping} to take request and pass to service
     *
     * @param forgotPasswordRequest request coming from client with email address
     * @return response with status message that email sent or invalid user email address
     */
    @PostMapping(value = "/forgot-password")
    public HttpSuccessResponse<String> forgotPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        return forgotPasswordService.forgotPassword(forgotPasswordRequest);
    }

}
