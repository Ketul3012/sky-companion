/**
 * Reset Password endpoint rest controller
 * <p>
 * This class is responsible for handling the reset password functionality in
 * the application.
 * It receives a POST request with a reset password request object containing a
 * token and a new password.
 * The request is validated using the @Valid annotation and passed to the
 * ResetPasswordService for further processing.
 * The ResetPasswordService performs the necessary logic to reset the password
 * and returns a response indicating
 * whether the password reset was successful or not.
 * <p>
 * This file is important to the project as it provides the REST API endpoint
 * for resetting passwords,
 * allowing users to securely reset their passwords when they have forgotten
 * them or want to change them.
 */

package com.sky.companion.authentication.reset_password.controller;

import com.sky.companion.authentication.reset_password.request.ResetPasswordRequest;
import com.sky.companion.authentication.reset_password.service.ResetPasswordService;
import com.sky.companion.common.model.HttpSuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ResetPasswordController {

    @Autowired
    private ResetPasswordService resetPasswordService;

    /**
     * Method to declare forgot password endpoint created using {@link PostMapping}
     * to take request and pass to service
     *
     * @param resetPasswordRequest request coming from client with token and
     *                             password
     * @return response with status message that password reset successfully or not
     */
    @PostMapping(value = "/reset-password")
    public HttpSuccessResponse<String> resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
        return resetPasswordService.resetPassword(resetPasswordRequest);
    }

}
