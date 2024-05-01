/**
 * Reviews repository interface to handle CRUD operations for
 * {@link ReviewsEntity} using {@link JpaRepository}.
 * This interface provides methods to perform CRUD (Create, Read, Update,
 * Delete) operations on the database table
 * associated with the {@link ReviewsEntity} entity. It extends the
 * {@link JpaRepository} interface, which provides
 * generic implementations for these operations.
 * <p>
 * The {@link ReviewsRepository} is an important component of the project as it
 * acts as a bridge between the application
 * and the database, allowing the application to interact with the reviews data.
 * It provides a convenient way to perform
 * database operations without writing SQL queries manually.
 */

package com.sky.companion.reviews.common.repository;

import com.sky.companion.reviews.common.entity.ReviewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Reviews repository interface to handle CRUD operations for {@link ReviewsEntity} using {@link JpaRepository}
 */
public interface ReviewsRepository extends JpaRepository<ReviewsEntity, Integer> {
    List<ReviewsEntity> findByCompanionId(Integer companionId);
}
