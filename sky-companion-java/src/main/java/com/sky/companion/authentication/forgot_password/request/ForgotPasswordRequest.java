/**
 * This class represents a request for the forgot password functionality.
 * It is used to capture the email address of the user who wants to reset their password.
 * The email address is validated to ensure it is not empty and in the proper format.
 * This class is important to the project as it provides a structured way to handle forgot password requests.
 */

package com.sky.companion.authentication.forgot_password.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class ForgotPasswordRequest {

    @NotEmpty(message = "email address cannot be empty")
    @Email(message = "email should be in proper format")
    private String email;

    public ForgotPasswordRequest() {
    }

    public ForgotPasswordRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
