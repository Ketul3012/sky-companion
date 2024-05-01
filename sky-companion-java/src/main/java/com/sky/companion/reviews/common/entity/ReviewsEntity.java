/**
 * This class represents the entity for the "reviews" table in the database.
 * It is used to handle CRUD operations for the reviews table.
 * <p>
 * The ReviewsEntity class contains information about a review, including the
 * user who gave the review,
 * the companion user who received the review, the rating given, and the review
 * description.
 * <p>
 * This entity is important to the project as it allows the application to store
 * and retrieve reviews
 * from the database, enabling users to give ratings and reviews to companion
 * users.
 */

package com.sky.companion.reviews.common.entity;

import com.sky.companion.authentication.common.entity.UserEntity;
import com.sky.companion.common.model.AuditModel;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "reviews")
public class ReviewsEntity extends AuditModel {
    private final int maxRating = 5;
    private final int minRating = 0;

    /**
     * auto generated id, used as a primary key
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * reference to user table, reviews can be given by user
     */
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;

    /**
     * reference to user table, reviews can be given to a companion user
     */
    @ManyToOne
    @JoinColumn(name = "companion_id", referencedColumnName = "id", nullable = false)
    private UserEntity companion;

    /**
     * rating given to a companion user
     */
    @Min(value = 0, message = "Rating must be at least 0")
    @Max(value = 5, message = "Rating must be at most 5")
    @Column(name = "rating")
    private Integer rating;

    /**
     * review given to a companion user
     */
    @Column(name = "description")
    private String description;

    public ReviewsEntity() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return this.user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public UserEntity getCompanion() {
        return this.companion;
    }

    public void setCompanion(UserEntity companion) {
        this.companion = companion;
    }

    public @Min(value = minRating, message = "Rating must be at least 0") @Max(value = maxRating, message = "Rating must be at most 5") Integer getRating() {
        return this.rating;
    }

    public void setRating(@Min(value = minRating, message = "Rating must be at least 0") @Max(value = maxRating, message = "Rating must be at most 5") Integer rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
