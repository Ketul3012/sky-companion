/**
 * This file, UserLanguageRepository.java, is a repository interface that provides methods for accessing and manipulating user language data in the authentication module of the Sky Companion project.
 * <p>
 * The UserLanguageRepository interface is an important component of the project as it defines the contract for interacting with the underlying data source for user language information. It allows other components in the project to perform CRUD (Create, Read, Update, Delete) operations on user language data.
 * <p>
 * By implementing this interface, classes can provide the necessary functionality to store, retrieve, update, and delete user language information, ensuring data integrity and consistency within the authentication module.
 */
package com.sky.companion.authentication.common.repository;

import com.sky.companion.authentication.common.entity.UserLanguageEntity;
import com.sky.companion.authentication.common.entity.UserLanguageId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserLanguageRepository extends JpaRepository<UserLanguageEntity, UserLanguageId> {

    @Query(value = "select * from user_language where user_id=:user_id", nativeQuery = true)
    List<UserLanguageEntity> findLanguageEntitiesForUser(@Param("user_id") Integer userId);

}
