/**
 * This file represents the UserRepository interface, which is responsible for
 * handling CRUD operations for {@link UserEntity} using {@link JpaRepository}.
 * It extends the JpaRepository interface, allowing it to leverage the built-in
 * methods for data access and manipulation.
 * The UserRepository is an important component of the project as it provides an
 * abstraction layer for interacting with the user table in the database.
 * It allows the application to perform operations such as finding a user by
 * email, finding a user by verification token, and finding a user by reset
 * password token.
 * By using this repository, the application can easily perform common database
 * operations on UserEntity objects without writing boilerplate code.
 */

package com.sky.companion.authentication.common.repository;

import com.sky.companion.authentication.common.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    /**
     * finds the data from the user table that has the given email.
     *
     * @param email
     * @return {@link UserEntity}
     */
    Optional<UserEntity> findByEmail(String email);

    UserEntity findByVerificationToken(String token);

    /**
     * this method finds user details based on unique reset password token
     *
     * @param resetPasswordToken reset password token using which user details will
     *                           be fetched
     * @return {@link UserEntity} or null based on valid token
     */
    UserEntity findByResetPasswordToken(String resetPasswordToken);
}
