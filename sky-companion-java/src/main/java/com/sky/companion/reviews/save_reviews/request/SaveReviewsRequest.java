/**
 * This class represents a request object for the save-reviews endpoint.
 * It is used to send data to the server for saving reviews.
 * The class contains fields for companionId, description, and rating.
 * The companionId represents the ID of the companion for which the review is
 * being saved.
 * The description represents the text description of the review.
 * The rating represents the rating given to the companion.
 * <p>
 * This class is important to the project as it defines the structure of the
 * request payload
 * for saving reviews. It ensures that the required fields are present and
 * validates the input
 * using annotations such as @NotEmpty, @Size, @Min, and @Max.
 */

package com.sky.companion.reviews.save_reviews.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SaveReviewsRequest {

    private int companionId;

    @NotEmpty(message = "Description cannot be empty")
    @Size(min = 2, message = "Description must be at least 2 characters long")
    private String description;

    @Min(value = 0, message = "Rating must be at least 0")
    @Max(value = 5, message = "Rating must be at most 5")
    private Integer rating;

    public SaveReviewsRequest() {
    }

    public int getCompanionId() {
        return companionId;
    }

    public void setCompanionId(int companionId) {
        this.companionId = companionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
