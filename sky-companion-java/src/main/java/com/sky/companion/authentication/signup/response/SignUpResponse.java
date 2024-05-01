/**
 * The SignUpResponse class represents the response object for the sign-up operation.
 * It contains the user's ID, first name, and last name.
 * This class is important to the project as it provides a structured response format for sign-up requests.
 */
package com.sky.companion.authentication.signup.response;

public class SignUpResponse {

    private Integer id;

    private String firstName;

    private String lastName;

    public SignUpResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
