/**
 * This interface represents a service to get user details by ID.
 * It is an important file in the project as it defines the contract for retrieving user information.
 * The implementation of this interface will check the existence of the user in the database and return the response.
 * If the user is not found, it will throw a UserNotFoundException.
 */

package com.sky.companion.authentication.get_user.service;

import com.sky.companion.authentication.common.dto.UserDTO;
import com.sky.companion.common.exceptions.UserNotFoundException;
import com.sky.companion.common.model.HttpSuccessResponse;

public interface GetUserService {

    /**
     * Retrieves the user details based on the provided user ID.
     *
     * @param userId the ID of the user to retrieve
     * @return an HttpSuccessResponse containing the user details
     * @throws UserNotFoundException if the user with the given ID is not found
     */
    HttpSuccessResponse<UserDTO> getUserById(Integer userId) throws UserNotFoundException;

}
