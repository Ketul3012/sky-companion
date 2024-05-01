/**
 * Signup service interface to declare methods for signup functionality
 */

package com.sky.companion.authentication.signup.service;

import com.sky.companion.authentication.signup.request.SignUpRequest;
import com.sky.companion.authentication.signup.response.SignUpResponse;
import com.sky.companion.common.model.HttpSuccessResponse;

public interface SignUpService {

    /**
     * Performs the signup functionality by taking all the required fields as a
     * request and saving them to the database.
     *
     * @param signUpRequest the signup request containing the required fields
     * @return an HTTP success response containing the signup response
     */
    HttpSuccessResponse<SignUpResponse> signUp(SignUpRequest signUpRequest);
}
