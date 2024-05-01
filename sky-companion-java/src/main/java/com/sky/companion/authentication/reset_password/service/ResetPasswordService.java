/**
 * This interface defines the contract for the reset password functionality
 * service.
 * It provides a method to validate the token and password in the request and
 * reset the password in the database.
 * This file is important to the project as it defines the behavior and
 * functionality of the reset password service,
 * which is a crucial part of the authentication process.
 */

package com.sky.companion.authentication.reset_password.service;

import com.sky.companion.authentication.reset_password.request.ResetPasswordRequest;
import com.sky.companion.common.model.HttpSuccessResponse;

public interface ResetPasswordService {

    /**
     * This method validates the token and password in the request and resets the
     * password in the database.
     *
     * @param resetPasswordRequest the request for the reset password service passed
     *                             from the controller
     * @return an {@link HttpSuccessResponse} with a string type to inform the user
     * about the API response
     */
    HttpSuccessResponse<String> resetPassword(ResetPasswordRequest resetPasswordRequest);

}
