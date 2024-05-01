/**
 * The EmailVerificationService interface provides methods for sending verification emails and verifying email tokens.
 * This file is important to the project as it defines the contract for email verification functionality.
 * It allows other components in the project to send verification emails and verify email tokens in a standardized way.
 */

package com.sky.companion.authentication.email_verification.service;

public interface EmailVerificationService {
    /**
     * Sends a verification email to the specified email address with the provided verification link.
     *
     * @param toEmail          the email address to send the verification email to
     * @param verificationLink the link to be included in the verification email
     */
    void sendVerificationEmail(String toEmail, String verificationLink);

    /**
     * Verifies the email using the provided token.
     *
     * @param token the token to verify the email
     * @return the verified email address
     */
    String verifyEmail(String token);
}
