/**
 * This class represents the UserEntity entity, which is equivalent to the user
 * table in the database.
 * It is used to handle CRUD operations for the user table.
 * The UserEntity class contains various attributes that represent the columns
 * of the user table,
 * along with their respective getters and setters.
 * It also includes relationships with other entities such as AddressEntity,
 * ReviewsEntity, CompanionProviderTripEntity,
 * SearchAlertsEntity, and LanguageEntity.
 * This class extends the AuditModel class, which provides auditing fields like
 * createdBy, createdDate, lastModifiedBy, and lastModifiedDate.
 * The UserEntity class is an important part of the project as it represents the
 * user data and allows for user-related operations.
 */

package com.sky.companion.authentication.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sky.companion.common.model.AuditModel;
import com.sky.companion.home.common.entity.CompanionProviderTripEntity;
import com.sky.companion.home.common.entity.SearchAlertsEntity;
import com.sky.companion.reviews.common.entity.ReviewsEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class UserEntity extends AuditModel {

    /**
     * auto generated id, used as a primary key
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * user's first name
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * user's last name
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * user email address
     */
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    /**
     * user password stored as a hash
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * flag to declare that user is active or not
     */
    @Column(name = "is_active", nullable = false, columnDefinition = "boolean default false")
    private Boolean isActive;
    /**
     * storing the verification token for the user to verify the email
     */
    private String verificationToken;

    /**
     * url of user's profile picture
     */
    @Column(name = "profile_picture")
    private String profilePicture;

    /**
     * user's date of birth in MM/DD/YYYY format
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    @Column(name = "dob", nullable = false)
    private Date dob;

    /**
     * user's mobile number without country code
     */
    @Column(name = "mobile_number", length = 10, nullable = false)
    private String mobileNumber;

    /**
     * when user do reset password then random token generated and stored here
     */
    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    /**
     * reference to address table, one user can be located at one address at a time
     */
    @OneToOne
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    /**
     * reference to reviews table, one user can have multiple reviews
     */
    @OneToMany(mappedBy = "companion", fetch = FetchType.EAGER)
    private Set<ReviewsEntity> reviews;

    /**
     * reference to companion_provider_trip table, one user can post multiple trips
     */
    @OneToMany(mappedBy = "id")
    private List<CompanionProviderTripEntity> trips;

    /**
     * reference to search_alerts table, one user can have multiple search_alerts
     */
    @OneToMany(mappedBy = "id")
    private List<SearchAlertsEntity> searchAlerts;

    /**
     * reference to user_language table, one user can have multiple languages
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_language", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "language_id", referencedColumnName = "id"))
    private Set<LanguageEntity> languages;

    public UserEntity() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
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

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public Set<ReviewsEntity> getReviews() {
        return reviews;
    }

    public void setReviews(Set<ReviewsEntity> reviews) {
        this.reviews = reviews;
    }

    public List<CompanionProviderTripEntity> getTrips() {
        return trips;
    }

    public void setTrips(List<CompanionProviderTripEntity> trips) {
        this.trips = trips;
    }

    public List<SearchAlertsEntity> getSearchAlerts() {
        return searchAlerts;
    }

    public void setSearchAlerts(List<SearchAlertsEntity> searchAlerts) {
        this.searchAlerts = searchAlerts;
    }

    public Set<LanguageEntity> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<LanguageEntity> languages) {
        this.languages = languages;
    }
}
