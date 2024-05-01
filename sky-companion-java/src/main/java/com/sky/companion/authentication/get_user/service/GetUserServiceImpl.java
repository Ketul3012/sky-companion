/**
 * This file contains the implementation of the {@link GetUserService}.
 * It provides the functionality to retrieve a user by their ID.
 * <p>
 * The GetUserServiceImpl class is an important part of the project as it handles the logic for retrieving user information.
 * It uses the UserRepository to fetch the user entity from the database and then maps it to a UserDTO using the UserMapper.
 * If the user is found, it returns a success response with the user details.
 * If the user is not found, it throws a UserNotFoundException.
 */

package com.sky.companion.authentication.get_user.service;

import com.sky.companion.authentication.common.dto.UserDTO;
import com.sky.companion.authentication.common.entity.UserEntity;
import com.sky.companion.authentication.common.mapper.UserMapper;
import com.sky.companion.authentication.common.repository.UserRepository;
import com.sky.companion.common.constants.ResponseMessagesConstants;
import com.sky.companion.common.constants.ResponseStatusCodeConstants;
import com.sky.companion.common.exceptions.UserNotFoundException;
import com.sky.companion.common.model.HttpSuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetUserServiceImpl implements GetUserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpSuccessResponse<UserDTO> getUserById(Integer userId) throws UserNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if (userEntity.isPresent()) {
            return new HttpSuccessResponse<>(ResponseStatusCodeConstants.SUCCESS, ResponseMessagesConstants.SUCCESS, UserMapper.entityToDTO(userEntity.get()));
        }
        throw new UserNotFoundException();
    }
}
