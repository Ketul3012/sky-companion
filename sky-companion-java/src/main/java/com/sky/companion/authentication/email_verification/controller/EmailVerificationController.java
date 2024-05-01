/**
 * The EmailVerificationController class is responsible for handling HTTP requests related to email verification.
 * It provides an endpoint for verifying email addresses using a token.
 * This controller is an important part of the authentication process in the project.
 * It interacts with the UserRepository and EmailVerificationService to perform the necessary operations.
 */
package com.sky.companion.authentication.email_verification.controller;

import com.sky.companion.authentication.common.repository.UserRepository;
import com.sky.companion.authentication.email_verification.service.EmailVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailVerificationController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailVerificationService emailVerificationService;

    /**
     * Handles the GET request for email verification.
     * Verifies the email address associated with the provided token.
     *
     * @param token the verification token
     * @return a string indicating the result of the verification process
     */
    @GetMapping("/verify")
    public String verifyEmail(@RequestParam("token") String token) {
        return emailVerificationService.verifyEmail(token);
    }
}
