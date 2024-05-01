/**
 * Get user endpoint rest controller
 * This class is responsible for handling HTTP requests related to retrieving user details.
 * It provides an endpoint to fetch user details by their ID.
 * The class uses the GetUserService to retrieve the user details and returns the result in the form of a HttpSuccessResponse.
 * If the user is not found, a UserNotFoundException is thrown.
 * This file is important to the project as it defines the API endpoint for retrieving user details and connects it to the corresponding service.
 */

package com.sky.companion.authentication.get_user.controller;

import com.sky.companion.authentication.common.dto.UserDTO;
import com.sky.companion.authentication.get_user.service.GetUserService;
import com.sky.companion.common.exceptions.UserNotFoundException;
import com.sky.companion.common.model.HttpSuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetUserController {

    @Autowired
    private GetUserService getUserService;

    /**
     * Method to declare get user endpoint created using {@link GetMapping} with id as path variable and pass to service to fetch user details.
     *
     * @param id id of user whose details needs to be fetched.
     * @return Found user dto or error message
     * @throws UserNotFoundException if the user with the given id is not found.
     */
    @GetMapping("/get-user/{id}")
    HttpSuccessResponse<UserDTO> getUserById(@PathVariable(value = "id") Integer id) throws UserNotFoundException {
        return getUserService.getUserById(id);
    }
}
