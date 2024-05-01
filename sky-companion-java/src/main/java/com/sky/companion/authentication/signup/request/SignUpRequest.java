/**
 * The SignUpRequest class represents the request object for user sign up in the authentication module.
 * It contains the necessary fields for creating a new user account, such as first name, last name, email, password, date of birth, and mobile number.
 * This class is important to the project as it defines the structure of the request payload for user sign up and provides validation constraints for each field.
 * By using this class, the application can ensure that the incoming sign up requests are valid and meet the required criteria.
 */
package com.sky.companion.authentication.signup.request;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.*;
import java.util.Date;

public class SignUpRequest {

    @NotEmpty(message = "First Name cannot be empty")
    @Size(min = 2, message = "First Name must be at least 2 characters long")
    private String firstName;

    @NotEmpty(message = "Last Name cannot be empty")
    @Size(min = 2, message = "Last Name must be at least 2 characters long")
    private String lastName;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be in proper format")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    private Boolean isActive;

    @NotEmpty(message = "Confirm Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String confirmPassword;

    @NotNull(message = "Date of Birth cant be empty")
    @Past
    private Date dob;

    @NotEmpty(message = "Mobile Number cannot be empty")
    @NumberFormat
    private String mobileNumber;

    public SignUpRequest() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
