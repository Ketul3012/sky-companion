/**
 * This class represents a request for resetting a password in the application.
 * It is used to encapsulate the necessary information required to reset a user's password.
 * The class contains two fields: 'token' and 'newPassword'.
 * The 'token' field represents the unique token associated with the password reset request.
 * The 'newPassword' field represents the new password that the user wants to set.
 * <p>
 * This class is important to the project as it provides a standardized way to handle password reset requests.
 * It ensures that the required fields are present and not empty, as specified by the validation constraints.
 * By using this class, the application can easily validate and process password reset requests.
 */
package com.sky.companion.authentication.reset_password.request;

import javax.validation.constraints.NotEmpty;

/**
 * request class for reset password functionality
 */
public class ResetPasswordRequest {

    @NotEmpty(message = "token cannot be empty")
    private String token;

    @NotEmpty(message = "password cannot be empty")
    private String newPassword;

    public ResetPasswordRequest() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
