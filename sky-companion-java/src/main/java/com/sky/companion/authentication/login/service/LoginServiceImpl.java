/**
 * This file is the implementation class for the {@link LoginService}.
 * It provides the functionality to authenticate a user based on the provided
 * login credentials.
 * The login method takes a LoginRequest object containing user login
 * information and returns an HttpSuccessResponse.
 * If the authentication is successful, the HttpSuccessResponse contains a
 * LoginResponse.
 * If the authentication fails, the HttpSuccessResponse contains an error
 * message.
 * <p>
 * This file is important to the project as it handles the login functionality
 * and interacts with the UserRepository,
 * JWTUtils, and PasswordEncoder to authenticate the user and generate a JWT
 * token.
 */

package com.sky.companion.authentication.login.service;

import com.sky.companion.authentication.common.entity.UserEntity;
import com.sky.companion.authentication.common.repository.UserRepository;
import com.sky.companion.authentication.login.request.LoginRequest;
import com.sky.companion.authentication.login.response.LoginResponse;
import com.sky.companion.common.constants.ResponseMessagesConstants;
import com.sky.companion.common.constants.ResponseStatusCodeConstants;
import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.security.jwt.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Authenticates a user based on the provided login credentials.
     *
     * @param loginRequest The LoginRequest object containing user login
     *                     information.
     * @return An HttpSuccessResponse containing a LoginResponse if authentication
     * is successful,
     * or an HttpSuccessResponse with an error message if authentication
     * fails.
     */
    @Override
    public HttpSuccessResponse<LoginResponse> login(LoginRequest loginRequest) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(loginRequest.getEmail());
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            if (userEntity.getActive() == Boolean.FALSE) {
                return new HttpSuccessResponse<>(ResponseStatusCodeConstants.BAD_REQUEST, "User not verified", null);
            }
            if (validatePassword(userEntity.getPassword(), loginRequest.getPassword())) {
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setId(userEntity.getId());
                loginResponse.setFirstName(userEntity.getFirstName());
                loginResponse.setLastName(userEntity.getLastName());
                loginResponse.setEmail(userEntity.getEmail());
                loginResponse.setJwtToken(jwtUtils.generateJWTToken(userEntity));

                return new HttpSuccessResponse<>(ResponseStatusCodeConstants.SUCCESS, ResponseMessagesConstants.SUCCESS,
                        loginResponse);
            } else {
                return new HttpSuccessResponse<>(ResponseStatusCodeConstants.UNAUTHORIZED, "Invalid user credentials",
                        null);
            }

        } else {
            return new HttpSuccessResponse<>(ResponseStatusCodeConstants.UNAUTHORIZED, "Invalid user credentials",
                    null);
        }
    }

    private boolean validatePassword(String hashedPassword, String simplePassword) {
        return passwordEncoder.matches(simplePassword, hashedPassword);
    }

}
