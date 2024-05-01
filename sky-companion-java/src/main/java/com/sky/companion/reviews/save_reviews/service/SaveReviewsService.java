/**
 * Save Reviews service interface to declare methods for save reviews
 * functionality.
 * This file defines the contract for the SaveReviewsService, which is
 * responsible for saving reviews.
 * It contains a single method, saveReviews, that takes a SaveReviewsRequest
 * object and a UserDTO object as parameters.
 * The method saves the reviews and returns an HttpSuccessResponse object
 * containing the saved review and a status message.
 * <p>
 * This file is important to the project as it provides the interface for saving
 * reviews, allowing other classes to implement this functionality.
 * It ensures that the saveReviews method is implemented consistently across
 * different implementations of the SaveReviewsService interface.
 */

package com.sky.companion.reviews.save_reviews.service;

import com.sky.companion.authentication.common.dto.UserDTO;
import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.reviews.save_reviews.request.SaveReviewsRequest;

public interface SaveReviewsService {
    /**
     * saveReviews method to implement functionality for saving reviews.
     *
     * @param saveReviewsRequest
     * @param user
     * @return the saved review and status message.
     */
    HttpSuccessResponse<String> saveReviews(SaveReviewsRequest saveReviewsRequest, UserDTO user);
}
