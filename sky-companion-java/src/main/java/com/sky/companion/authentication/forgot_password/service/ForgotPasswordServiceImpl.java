/**
 * This file is part of the Sky Companion project and contains the implementation of the {@link ForgotPasswordService} interface.
 * It provides the functionality to handle the forgot password feature for user authentication.
 * <p>
 * The ForgotPasswordServiceImpl class implements the methods defined in the ForgotPasswordService interface.
 * It is responsible for generating a reset password token, saving it to the user entity, and sending a reset password email to the user.
 * <p>
 * This file is important to the project as it enables users to reset their passwords in case they forget it.
 * It integrates with the UserRepository to retrieve user information and the JavaMailSender to send reset password emails.
 * <p>
 * The forgotPassword method takes a ForgotPasswordRequest object as input and performs the following steps:
 * 1. Retrieves the user entity from the UserRepository based on the provided email address.
 * 2. If the user entity is not found, it returns an error response indicating an invalid email address.
 * 3. Generates a reset password token using UUID.randomUUID().
 * 4. Sets the reset password token in the user entity and saves it using the UserRepository.
 * 5. Sends a reset password email to the user's email address using the JavaMailSender.
 * 6. Returns a success response indicating that the email has been sent successfully.
 * <p>
 * The sendResetPasswordEmail method is a private helper method that constructs and sends the reset password email.
 * It takes the email address and reset password token as input and uses the JavaMailSender to send the email.
 */

package com.sky.companion.authentication.forgot_password.service;

import com.sky.companion.authentication.common.entity.UserEntity;
import com.sky.companion.authentication.common.repository.UserRepository;
import com.sky.companion.authentication.forgot_password.request.ForgotPasswordRequest;
import com.sky.companion.common.constants.ResponseMessagesConstants;
import com.sky.companion.common.constants.ResponseStatusCodeConstants;
import com.sky.companion.common.model.HttpSuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserRepository userRepository;

    @Value("${reset.password.email.link}")
    private String resetPasswordLink;

    /**
     * {@inheritDoc}
     *
     * @param forgotPasswordRequest request for service passed from controller
     * @return
     */
    @Override
    public HttpSuccessResponse<String> forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
        UserEntity userEntity = userRepository.findByEmail(forgotPasswordRequest.getEmail()).orElse(null);
        if (userEntity == null) {
            return new HttpSuccessResponse<>(ResponseStatusCodeConstants.BAD_REQUEST, ResponseMessagesConstants.INVALID_USER_CREDENTIALS, "Invalid email address");
        }
        String resetPasswordToken = UUID.randomUUID().toString();
        userEntity.setResetPasswordToken(resetPasswordToken);
        userRepository.save(userEntity);
        try {
            sendResetPasswordEmail(userEntity.getEmail(), userEntity.getResetPasswordToken());
        } catch (Exception e) {
            return new HttpSuccessResponse<>(ResponseStatusCodeConstants.INTERNAL_SERVER_ERROR, ResponseMessagesConstants.INTERNAL_SERVER_ERROR, "System not able to send email");
        }
        return new HttpSuccessResponse<>(ResponseStatusCodeConstants.SUCCESS, ResponseMessagesConstants.SUCCESS, "Email sent successfully");
    }

    /**
     * this method send reset password email
     *
     * @param emailAddress       email address where email needs to be sent
     * @param resetPasswordToken reset password token send with email
     */
    private void sendResetPasswordEmail(String emailAddress, String resetPasswordToken) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailAddress);
        message.setSubject("SkyCompanion Reset password");
        String resetPasswordEmailLink = resetPasswordLink + resetPasswordToken;
        message.setText("Dear User,\n To reset your password, please click the following link:\n" + resetPasswordEmailLink);
        javaMailSender.send(message);
    }
}
