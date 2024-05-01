/**
 * This class represents a Data Transfer Object (DTO) for the ReviewsEntity class.
 * It is used to transmit reviews details over the network.
 * <p>
 * The ReviewsDTO class contains the following properties:
 * - id: The auto-generated ID of the review.
 * - userId: The ID of the user who wrote the review.
 * - userFirstName: The first name of the user who wrote the review.
 * - userLastName: The last name of the user who wrote the review.
 * - companionId: The ID of the companion being reviewed.
 * - companionFirstName: The first name of the companion being reviewed.
 * - companionLastName: The last name of the companion being reviewed.
 * - rating: The rating given to the companion.
 * - description: The description of the review.
 * - createdStamp: The timestamp when the review was created.
 * <p>
 * This class is important to the project as it provides a standardized way to transfer review data
 * between different components of the system, such as the frontend and backend. It helps in maintaining
 * loose coupling and separation of concerns by encapsulating review data in a separate object.
 */

package com.sky.companion.reviews.common.dto;

import java.util.Date;

public class ReviewsDTO {
    /**
     * auto generated id
     */
    private Integer id;

    /**
     * user id
     */
    private Integer userId;

    /**
     * user firstname
     */
    private String userFirstName;

    /**
     * user lastname
     */
    private String userLastName;

    /**
     * companion id
     */
    private Integer companionId;

    /**
     * companion firstname
     */
    private String companionFirstName;

    /**
     * companion lastname
     */
    private String companionLastName;

    /**
     * companion rating
     */
    private Integer rating;

    /**
     * companion reviews description
     */
    private String description;

    private Date createdStamp;

    public ReviewsDTO() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return this.userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return this.userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public Integer getCompanionId() {
        return this.companionId;
    }

    public void setCompanionId(Integer companionId) {
        this.companionId = companionId;
    }

    public String getCompanionFirstName() {
        return this.companionFirstName;
    }

    public void setCompanionFirstName(String companionFirstName) {
        this.companionFirstName = companionFirstName;
    }

    public String getCompanionLastName() {
        return this.companionLastName;
    }

    public void setCompanionLastName(String companionLastName) {
        this.companionLastName = companionLastName;
    }

    public Integer getRating() {
        return this.rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedStamp() {
        return this.createdStamp;
    }

    public void setCreatedStamp(Date createdStamp) {
        this.createdStamp = createdStamp;
    }
}
