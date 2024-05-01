/**
 * This class represents the response object for the login endpoint.
 * It contains the user's information and the JWT token generated upon successful login.
 * <p>
 * The LoginResponse class is an important part of the project as it is used to send the login response
 * back to the client. It encapsulates the user's details such as their ID, first name, last name, email,
 * and the JWT token. This information is crucial for the client to authenticate and authorize subsequent
 * requests to the server.
 */
package com.sky.companion.authentication.login.response;

public class LoginResponse {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String jwtToken;

    public LoginResponse() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
