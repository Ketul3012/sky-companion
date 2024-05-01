/**
 * This class represents a Data Transfer Object (DTO) for the
 * {@link com.sky.companion.authentication.common.entity.AddressEntity} in the
 * Sky Companion project.
 * It is used to transmit user address details over the network.
 * <p>
 * The AddressDto class contains the following properties:
 * - id: The unique identifier for the address.
 * - addressLine1: The first line of the address.
 * - addressLine2: The second line of the address.
 * - postalCode: The postal or zip code of the area.
 * - city: The city of the address.
 * - province: The province or state of the address.
 * - country: The country of the address.
 * <p>
 * This class is important to the project as it allows the application to
 * transfer address information between different components and across the
 * network. It helps in decoupling the domain model from the network
 * communication, ensuring that only the necessary address details are
 * transmitted.
 */

package com.sky.companion.authentication.common.dto;

public class AddressDto {

    /**
     * unique id
     */
    private Integer id;

    /**
     * address line 1
     */
    private String addressLine1;

    /**
     * address line 2
     */
    private String addressLine2;

    /**
     * postal or zip code of area
     */
    private String postalCode;

    /**
     * city
     */
    private String city;

    /**
     * province or state
     */
    private String province;

    /**
     * country
     */
    private String country;

    public AddressDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
