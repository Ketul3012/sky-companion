/**
 * Mapper class used to run mapping from and to between {@link ReviewsEntity}
 * and {@link ReviewsDTO}.
 * This class provides methods to convert {@link ReviewsEntity} objects to
 * {@link ReviewsDTO} objects and vice versa.
 * It also includes a method to calculate the average rating from a set of
 * reviews.
 * This file is important to the project as it facilitates the conversion of
 * data between the entity and DTO classes,
 * allowing for seamless communication between different layers of the
 * application.
 */

package com.sky.companion.reviews.common.mapper;

import com.sky.companion.reviews.common.dto.ReviewsDTO;
import com.sky.companion.reviews.common.entity.ReviewsEntity;

import java.util.Set;

/**
 * Mapper class used to run mapping from and to between {@link ReviewsEntity} and {@link ReviewsDTO}
 */
public class ReviewsMapper {
    private static final Double multiplier = 10.0;
    private static final Double divider = 10.0;

    private ReviewsMapper() {
    }

    /**
     * maps {@link ReviewsEntity} to {@link ReviewsDTO}
     *
     * @param reviewsEntity
     * @return reviewsDto
     */
    public static ReviewsDTO entityToDTO(ReviewsEntity reviewsEntity) {
        ReviewsDTO reviewsDTO = new ReviewsDTO();
        reviewsDTO.setId(reviewsEntity.getId());
        reviewsDTO.setUserId(reviewsEntity.getUser().getId());
        reviewsDTO.setUserFirstName(reviewsEntity.getUser().getFirstName());
        reviewsDTO.setUserLastName(reviewsEntity.getUser().getLastName());
        reviewsDTO.setCompanionId(reviewsEntity.getCompanion().getId());
        reviewsDTO.setCompanionFirstName(reviewsEntity.getCompanion().getFirstName());
        reviewsDTO.setCompanionLastName(reviewsEntity.getCompanion().getLastName());
        reviewsDTO.setRating(reviewsEntity.getRating());
        reviewsDTO.setDescription(reviewsEntity.getDescription());
        reviewsDTO.setCreatedStamp(reviewsEntity.getCreatedStamp());
        return reviewsDTO;
    }

    /**
     * Logic to calculate average ratings from the list of reviews
     *
     * @param reviews reviews for which average needs to be calculated
     * @return average rating for all the reviews.
     */
    public static Double calculateRatings(Set<ReviewsEntity> reviews) {
        return Math.round(reviews.stream().mapToDouble(ReviewsEntity::getRating).average().orElse(0.0) * multiplier) / divider;
    }
}
