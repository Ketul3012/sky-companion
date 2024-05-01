/**
 * Login service interface to declare methods for login functionality
 * <p>
 * This interface defines the contract for the login service in the Sky
 * Companion project.
 * It declares the login method, which is responsible for validating user
 * credentials
 * (email and password) against a database and returning a response.
 * <p>
 * The LoginService interface is important to this project as it provides a
 * standardized way
 * to implement the login functionality across different components and modules.
 * By using this interface, different implementations of the login service can
 * be easily
 * swapped and integrated into the project without affecting the calling code.
 *
 * @see com.sky.companion.authentication.login.request.LoginRequest
 * @see com.sky.companion.authentication.login.response.LoginResponse
 * @see com.sky.companion.common.model.HttpSuccessResponse
 */

package com.sky.companion.authentication.login.service;

import com.sky.companion.authentication.login.request.LoginRequest;
import com.sky.companion.authentication.login.response.LoginResponse;
import com.sky.companion.common.model.HttpSuccessResponse;

public interface LoginService {

    /**
     * login method to implement login functionality by taking email and password as
     * a request
     * and validating those using database
     *
     * @param loginRequest the login request containing the user's email and
     *                     password
     * @return the HTTP success response containing the login response
     */
    HttpSuccessResponse<LoginResponse> login(LoginRequest loginRequest);

}
