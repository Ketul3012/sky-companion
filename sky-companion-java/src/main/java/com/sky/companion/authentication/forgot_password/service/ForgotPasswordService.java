/**
 * This interface defines the functionality for the forgot password service.
 * It is responsible for validating the email address provided in the request
 * and sending a reset password link to that email.
 * <p>
 * This file is important to the project as it provides the contract for the
 * forgot password service, allowing other components to interact with it
 * and utilize its functionality.
 */

package com.sky.companion.authentication.forgot_password.service;

import com.sky.companion.authentication.forgot_password.request.ForgotPasswordRequest;
import com.sky.companion.common.model.HttpSuccessResponse;

public interface ForgotPasswordService {

    /**
     * This method validates the email address in the request and sends a reset
     * password link to that email.
     *
     * @param forgotPasswordRequest the request for the service passed from the controller
     * @return an {@link HttpSuccessResponse} with a string type to inform the user about the API response
     */
    HttpSuccessResponse<String> forgotPassword(ForgotPasswordRequest forgotPasswordRequest);
}
