/**
 * The ModifyProfileRequest class represents a request object used for modifying user profiles.
 * It contains various fields such as first name, last name, date of birth, mobile number, address, and language preferences.
 * This class is important to the project as it serves as a data transfer object (DTO) for handling user profile modification requests.
 * The fields in this class are annotated with validation constraints to ensure that the provided data is valid and meets the required criteria.
 * By using this class, the application can receive and process requests to modify user profiles in a standardized and validated manner.
 */
package com.sky.companion.authentication.modify_profile.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

public class ModifyProfileRequest {

    @NotEmpty(message = "First name cannot be empty")
    private String firstname;

    @NotEmpty(message = "Last name cannot be empty")
    private String lastname;

    @NotNull(message = "Date cannot be null")
    @Past
    private Date dob;

    @NotEmpty(message = "Mobile number cannot be empty")
    @Pattern(regexp = "^[0-9]{1,10}$")
    private String mobileNumber;

    @NotEmpty(message = "Address line 1 cannot be empty")
    private String addressLine1;

    @NotEmpty(message = "Address line 2 cannot be empty")
    private String addressLine2;

    private String postalCode;

    private String city;

    private String province;

    private String country;

    private List<Integer> languages;

    public ModifyProfileRequest() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Integer> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Integer> languages) {
        this.languages = languages;
    }
}
