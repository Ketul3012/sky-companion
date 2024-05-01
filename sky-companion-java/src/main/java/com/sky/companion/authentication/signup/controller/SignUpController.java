/**
 * The SignUpController class is responsible for handling HTTP requests related to user sign up.
 * It receives a SignUpRequest object as a request body and validates it using the @Valid annotation.
 * The SignUpService is then called to process the sign up request and return a SignUpResponse.
 * The response is wrapped in an HttpSuccessResponse and returned to the client.
 * <p>
 * This file is important to the project as it provides the endpoint for user sign up functionality.
 * It acts as a controller in the MVC architecture and delegates the sign up request to the SignUpService.
 * By separating the concerns of handling HTTP requests and processing business logic, the codebase becomes more modular and maintainable.
 */
package com.sky.companion.authentication.signup.controller;

import com.sky.companion.authentication.signup.request.SignUpRequest;
import com.sky.companion.authentication.signup.response.SignUpResponse;
import com.sky.companion.authentication.signup.service.SignUpService;
import com.sky.companion.common.model.HttpSuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    /**
     * Handles the HTTP POST request for user sign up.
     *
     * @param signUpRequest The sign up request containing user details.
     * @return HttpSuccessResponse containing the sign up response.
     */
    @PostMapping(path = "/signup")
    public HttpSuccessResponse<SignUpResponse> signup(@Valid @RequestBody SignUpRequest signUpRequest) {
        return signUpService.signUp(signUpRequest);
    }

}
