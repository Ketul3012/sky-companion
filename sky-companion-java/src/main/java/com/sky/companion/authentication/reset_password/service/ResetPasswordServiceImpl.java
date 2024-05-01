/**
 * This file contains the implementation of the {@link ResetPasswordService}
 * interface.
 * It provides the functionality to reset the password for a user.
 * The ResetPasswordServiceImpl class is responsible for handling the reset
 * password logic.
 * It retrieves the user entity based on the reset password token provided in
 * the request,
 * updates the user's password using the PasswordEncoder, and saves the changes
 * to the UserRepository.
 * This file is important to the project as it enables users to reset their
 * passwords securely.
 */

package com.sky.companion.authentication.reset_password.service;

import com.sky.companion.authentication.common.entity.UserEntity;
import com.sky.companion.authentication.common.repository.UserRepository;
import com.sky.companion.authentication.reset_password.request.ResetPasswordRequest;
import com.sky.companion.common.constants.ResponseMessagesConstants;
import com.sky.companion.common.constants.ResponseStatusCodeConstants;
import com.sky.companion.common.model.HttpSuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * {@inheritDoc}
     *
     * @param resetPasswordRequest request for service passed from controller
     * @return {@link HttpSuccessResponse} with string type to inform user about api
     * responses
     */
    @Override
    public HttpSuccessResponse<String> resetPassword(ResetPasswordRequest resetPasswordRequest) {

        UserEntity userEntity = userRepository.findByResetPasswordToken(resetPasswordRequest.getToken());

        if (userEntity == null) {
            return new HttpSuccessResponse<>(ResponseStatusCodeConstants.BAD_REQUEST,
                    ResponseMessagesConstants.VALIDATION_FAILED, "Invalid reset password token");
        }

        userEntity.setPassword(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));
        userEntity.setResetPasswordToken(null);
        userRepository.save(userEntity);

        return new HttpSuccessResponse<>(ResponseStatusCodeConstants.SUCCESS, ResponseMessagesConstants.SUCCESS,
                "Password changed successfully, you can proceed with login");
    }
}
