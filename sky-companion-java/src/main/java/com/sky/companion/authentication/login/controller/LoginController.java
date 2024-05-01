/**
 * This class represents the LoginController in the project.
 * It is responsible for handling the login endpoint of the application.
 * The login endpoint allows users to authenticate and obtain a login response.
 * This controller is an essential part of the authentication process in the project.
 * It receives a LoginRequest object as input, validates it, and passes it to the LoginService for further processing.
 * The LoginService will then handle the authentication logic and return a LoginResponse object.
 * The login method in this controller is annotated with @PostMapping to map it to the "/login" endpoint.
 * It also uses the @Valid annotation to perform input validation on the LoginRequest object.
 * The response is wrapped in an HttpSuccessResponse object before being returned.
 */
package com.sky.companion.authentication.login.controller;

import com.sky.companion.authentication.login.request.LoginRequest;
import com.sky.companion.authentication.login.response.LoginResponse;
import com.sky.companion.authentication.login.service.LoginService;
import com.sky.companion.common.model.HttpSuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * Handles the login endpoint.
     *
     * @param loginRequest the LoginRequest object containing the user's login
     *                     credentials
     * @return an HttpSuccessResponse object containing the LoginResponse object
     * with the login status message
     */
    @PostMapping(value = "/login")
    public HttpSuccessResponse<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest);
    }
}
