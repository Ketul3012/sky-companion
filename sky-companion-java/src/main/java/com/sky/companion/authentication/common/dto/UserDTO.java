/**
 * This file represents a Data Transfer Object (DTO) for the
 * {@link com.sky.companion.authentication.common.entity.UserEntity} class in
 * the authentication module of the Sky Companion project.
 * It is used to transmit user details over the network.
 * <p>
 * The UserDTO class contains various properties such as id, firstName,
 * lastName, email, isActive, profilePicture, dob, mobileNumber, languages,
 * address, ratings, and reviews.
 * These properties represent the user's information, including their personal
 * details, contact information, language preferences, address, average ratings,
 * and reviews.
 * <p>
 * This file is important to the project as it serves as a bridge between the
 * backend and frontend components of the application.
 * It allows the user's data to be transferred and displayed accurately on the
 * frontend, ensuring a seamless user experience.
 */

package com.sky.companion.authentication.common.dto;

import com.sky.companion.reviews.common.dto.ReviewsDTO;

import java.util.Date;
import java.util.List;

public class UserDTO {

    /**
     * auto generated id
     */
    private Integer id;

    /**
     * user's first name
     */
    private String firstName;

    /**
     * user's last name
     */
    private String lastName;

    /**
     * user email address
     */
    private String email;

    /**
     * flag to declare that user is active or not
     */
    private Boolean isActive;

    /**
     * user's date of birth in MM/DD/YYYY format
     */
    private Date dob;

    /**
     * user's mobile number without country code
     */
    private String mobileNumber;

    /**
     * languages for user
     */
    private List<LanguageDto> languages;

    /**
     * address of user
     */
    private AddressDto address;
    /**
     * average ratings for the user
     */
    private Double ratings;

    /**
     * reviews for the user
     */
    private List<ReviewsDTO> reviews;

    public UserDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public List<LanguageDto> getLanguages() {
        return languages;
    }

    public void setLanguages(List<LanguageDto> languages) {
        this.languages = languages;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public Double getRatings() {
        return ratings;
    }

    public void setRatings(Double ratings) {
        this.ratings = ratings;
    }

    public List<ReviewsDTO> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewsDTO> reviews) {
        this.reviews = reviews;
    }
}
