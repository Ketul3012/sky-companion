/**
 * The LanguageRepository interface is responsible for handling CRUD operations for LanguageEntity objects.
 * It extends the JpaRepository interface, which provides convenient methods for interacting with the underlying database.
 * This repository is important to the project as it allows the application to perform database operations related to languages.
 * It provides methods for retrieving, saving, updating, and deleting LanguageEntity objects.
 */
package com.sky.companion.authentication.common.repository;

import com.sky.companion.authentication.common.entity.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<LanguageEntity, Integer> {
    LanguageEntity findById(int id);

}
