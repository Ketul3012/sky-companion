/**
 * This class represents the AddressEntity entity in the database.
 * It is used to handle CRUD operations for the "address" table.
 * The AddressEntity contains information about a physical address,
 * such as address lines, postal code, city, province/state, and country.
 * It is associated with the UserEntity class through a one-to-one relationship.
 * <p>
 * This file is important to the project because it defines the structure and
 * behavior
 * of the AddressEntity, which is a crucial component in the authentication
 * module.
 * It allows the application to store and retrieve address information for
 * users,
 * enabling features such as user registration, profile management, and address
 * validation.
 * The AddressEntity class extends the AuditModel class, which provides auditing
 * fields
 * such as created and updated timestamps, allowing for tracking of address
 * modifications.
 */
package com.sky.companion.authentication.common.entity;

import com.sky.companion.common.model.AuditModel;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class AddressEntity extends AuditModel {

    /**
     * auto generated id, used as a primary key
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * address line 1
     */
    @Column(name = "address_line_1", nullable = false)
    private String addressLine1;

    /**
     * address line 2
     */
    @Column(name = "address_line_2")
    private String addressLine2;

    /**
     * postal or zip code of area
     */
    @Column(name = "postal_code", length = 10)
    private String postalCode;

    /**
     * city
     */
    @Column(name = "city")
    private String city;

    /**
     * province or state
     */
    @Column(name = "province")
    private String province;

    /**
     * country
     */
    @Column(name = "country")
    private String country;

    public AddressEntity() {
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
