/**
 * This file is the implementation of the SaveReviewsService interface.
 * It provides the functionality to save reviews based on the provided request
 * and user information.
 * The SaveReviewsServiceImpl class is responsible for interacting with the
 * UserRepository and ReviewsRepository
 * to retrieve user and companion information and save the reviews in the
 * database.
 * <p>
 * This file is important to the project as it handles the business logic for
 * saving reviews,
 * ensuring that the reviews are associated with the correct user and companion
 * entities.
 * It also returns an HTTP success response with details of the saved review.
 */

package com.sky.companion.reviews.save_reviews.service;

import com.sky.companion.authentication.common.dto.UserDTO;
import com.sky.companion.authentication.common.entity.UserEntity;
import com.sky.companion.authentication.common.repository.UserRepository;
import com.sky.companion.common.constants.ResponseMessagesConstants;
import com.sky.companion.common.constants.ResponseStatusCodeConstants;
import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.reviews.common.entity.ReviewsEntity;
import com.sky.companion.reviews.common.repository.ReviewsRepository;
import com.sky.companion.reviews.save_reviews.request.SaveReviewsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SaveReviewsServiceImpl implements SaveReviewsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ReviewsRepository reviewsRepository;

    /**
     * Saves a new review based on the provided request and user information.
     *
     * @param saveReviewsRequest The request containing review details.
     * @param userDto            The DTO representing the user who is submitting the review.
     * @return An HTTP success response with details of the saved review.
     */
    @Override
    public HttpSuccessResponse<String> saveReviews(SaveReviewsRequest saveReviewsRequest, UserDTO userDto) {

        ReviewsEntity reviewsEntity = new ReviewsEntity();

        Optional<UserEntity> user = userRepository.findById(userDto.getId());
        if (user.isPresent()) {
            Optional<UserEntity> companion = userRepository.findById(saveReviewsRequest.getCompanionId());
            if (companion.isPresent()) {
                reviewsEntity.setCompanion(companion.get());
                reviewsEntity.setUser(user.get());
                reviewsEntity.setDescription(saveReviewsRequest.getDescription());
                reviewsEntity.setRating(saveReviewsRequest.getRating());

                reviewsRepository.save(reviewsEntity);

                return new HttpSuccessResponse<>(ResponseStatusCodeConstants.SUCCESS, ResponseMessagesConstants.SUCCESS, "Review saved successfully.");
            } else {
                return new HttpSuccessResponse<>(ResponseStatusCodeConstants.INVALID_USER, ResponseMessagesConstants.INVALID_USER, "companion not found.");
            }
        } else {
            return new HttpSuccessResponse<>(ResponseStatusCodeConstants.UNAUTHORIZED, ResponseMessagesConstants.INVALID_USER, "user not found");
        }
    }
}