/**
 * The EmailVerificationServiceImpl class is responsible for handling email verification functionality in the SkyCompanion project.
 * It implements the EmailVerificationService interface and provides methods for sending verification emails and verifying email tokens.
 * This class is important to the project as it ensures that user emails are verified before allowing them to log in.
 */

package com.sky.companion.authentication.email_verification.service;

import com.sky.companion.authentication.common.entity.UserEntity;
import com.sky.companion.authentication.common.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailVerificationServiceImpl implements EmailVerificationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendVerificationEmail(String toEmail, String verificationLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("SkyCompanion User Verification Mail");
        message.setText("Dear User,\n To verify your email, please click the following link:\n" + verificationLink);
        javaMailSender.send(message);
    }

    @Override
    public String verifyEmail(String token) {
        UserEntity user = userRepository.findByVerificationToken(token);

        if (user != null) {
            user.setActive(true);
            user.setVerificationToken(null);
            userRepository.save(user);

            return "Email verification successful. You can now log in.";
        } else {
            return "Invalid verification token. Please try again or contact support.";
        }
    }

}

