/**
 * This file contains the implementation of the {@link SignUpService} interface.
 * It provides the functionality to sign up a user by creating a new user
 * entity,
 * hashing the password, saving the user entity to the database, and sending a
 * verification email.
 * <p>
 * The SignUpServiceImpl class is an important part of the project as it handles
 * the sign-up process
 * and ensures that new users are registered correctly and their email addresses
 * are verified.
 * It interacts with the UserRepository to check if the email already exists in
 * the database,
 * and if not, it creates a new user entity, generates a verification token,
 * saves the user to the database,
 * and sends a verification email to the user's email address.
 * <p>
 * This file is located at
 * /Users/jahnaviprasad/Downloads/doc/Group05/sky-companion-java/src/main/java/com/sky/companion/authentication/signup/service/impl/SignUpServiceImpl.java
 */

package com.sky.companion.authentication.signup.service;

import com.sky.companion.authentication.common.entity.UserEntity;
import com.sky.companion.authentication.common.repository.UserRepository;
import com.sky.companion.authentication.email_verification.service.EmailVerificationService;
import com.sky.companion.authentication.signup.request.SignUpRequest;
import com.sky.companion.authentication.signup.response.SignUpResponse;
import com.sky.companion.common.constants.ResponseStatusCodeConstants;
import com.sky.companion.common.model.HttpSuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailVerificationService emailVerificationService;

    @Value("${verify.email.link}")
    private String emailVerificationLink;

    @Override
    public HttpSuccessResponse<SignUpResponse> signUp(SignUpRequest signUpRequest) {

        Optional<UserEntity> userEntity = userRepository.findByEmail(signUpRequest.getEmail());

        if (userEntity.isPresent()) {
            return new HttpSuccessResponse<>(ResponseStatusCodeConstants.BAD_REQUEST, "Email already exists", null);
        } else {
            UserEntity user = new UserEntity();

            String hashedPassword = passwordEncoder.encode(signUpRequest.getPassword());

            user.setFirstName(signUpRequest.getFirstName());
            user.setLastName(signUpRequest.getLastName());
            java.util.Date utilDob = signUpRequest.getDob();
            Date sqlDob = new Date(utilDob.getTime());
            user.setDob(sqlDob);
            user.setEmail(signUpRequest.getEmail());
            user.setMobileNumber(signUpRequest.getMobileNumber());
            user.setPassword(hashedPassword);
            user.setActive(false);

            String verificationToken = UUID.randomUUID().toString();
            user.setVerificationToken(verificationToken);

            userRepository.save(user);

            String verificationLink = emailVerificationLink + "?token=" + verificationToken;
            emailVerificationService.sendVerificationEmail(user.getEmail(), verificationLink);

            return new HttpSuccessResponse<>(ResponseStatusCodeConstants.SUCCESS,
                    "User registered successfully, Please check your email for verification link.", null);
        }
    }

}
