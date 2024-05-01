/**
 * Save Reviews endpoint rest controller
 * <p>
 * This class is responsible for handling HTTP requests related to saving
 * reviews for companions.
 * It defines an endpoint "/save-reviews" that accepts a POST request with a
 * JSON payload containing
 * the companionId, rating, and reviews. The request is validated using
 * the @Valid annotation.
 * The currently logged-in user is obtained from the @AuthenticationPrincipal
 * annotation.
 * The request is then passed to the SaveReviewsService for further processing.
 * The response is returned as an HttpSuccessResponse object containing a status
 * message.
 * <p>
 * This file is important to the project as it provides the implementation of
 * the save-reviews endpoint,
 * allowing users to save their reviews for companions. It acts as a bridge
 * between the client-side
 * application and the SaveReviewsService, which handles the business logic for
 * saving reviews.
 */

package com.sky.companion.reviews.save_reviews.controller;

import com.sky.companion.authentication.common.dto.UserDTO;
import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.reviews.save_reviews.request.SaveReviewsRequest;
import com.sky.companion.reviews.save_reviews.service.SaveReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Save Reviews endpoint rest controller
 */
@RestController
public class SaveReviewsController {

    @Autowired
    private SaveReviewsService saveReviewsService;

    /**
     * method to declare save-reviews endpoint created using {@link PostMapping} to take request and pass to service
     *
     * @param saveReviewsRequest object contains companionId, rating and reviews.
     * @param user               object is for the currently logged-in user who is writing reviews.
     * @return the status message with the review written by the user.
     */
    @PostMapping("/save-reviews")
    public HttpSuccessResponse<String> saveCompanionReview(@Valid @RequestBody SaveReviewsRequest saveReviewsRequest, @AuthenticationPrincipal UserDTO user) {
        return saveReviewsService.saveReviews(saveReviewsRequest, user);
    }
}
