/**
 * This class represents a request for the login endpoint.
 * It contains the email and password fields required for authentication.
 * The email field must be a valid email address and cannot be empty.
 * The password field cannot be empty.
 * <p>
 * This class is important to the project as it defines the structure of the request object
 * that is expected by the login endpoint. It ensures that the required fields are present
 * and validates the format of the email address.
 */
package com.sky.companion.authentication.login.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


public class LoginRequest {

    @NotEmpty(message = "email address cannot be empty")
    @Email(message = "email should be in proper format")
    private String email;

    @NotEmpty(message = "password cannot be empty")
    private String password;

    public LoginRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
