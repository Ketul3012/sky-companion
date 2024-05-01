/**
 * The CompanionProviderTripEntity class represents a trip entity for the companion provider in the Sky Companion project.
 * It is an entity class that maps to the "companion_provider_trip" table in the database.
 * <p>
 * This class defines the structure and properties of a trip, including the user who posted the trip, the source and destination of the trip,
 * the departure and arrival times, the approval status, the ticket price, and the list of stops for the trip.
 * <p>
 * This file is important to the project as it serves as a model for storing and retrieving trip data in the database.
 * It provides the necessary fields and relationships to represent a trip and its associated information.
 * The class also includes annotations for database mapping and validation purposes.
 */

package com.sky.companion.home.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sky.companion.authentication.common.entity.UserEntity;
import com.sky.companion.common.model.AuditModel;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "companion_provider_trip")
public class CompanionProviderTripEntity extends AuditModel {

    /**
     * auto generated id, used as a primary key
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * reference to user table, multiple trips can be posted by user
     */
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;

    /**
     * source, a place from where trip will begin
     */
    @Column(name = "source", nullable = false)
    private String source;

    /**
     * destination, a place from where trip will end
     */
    @Column(name = "destination", nullable = false)
    private String destination;

    /**
     * departure, a time when trip will begin
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss.SSS")
    @Column(name = "departure", nullable = false)
    private Date departure;

    /**
     * arrival, a time when trip will end
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss.SSS")
    @Column(name = "arrival", nullable = false)
    private Date arrival;

    /**
     * flag to identify post is approved or not
     */
    @Column(name = "is_approved", nullable = false, columnDefinition = "boolean default false")
    private Boolean isApproved;

    /**
     * ticket price
     */
    @Column(name = "price", columnDefinition = "float default 0.0")
    private Float price;

    /**
     * reference to stops table, one trip can have zero or more stops
     */
    @OneToMany(mappedBy = "trip", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<StopsEntity> stops;

    public CompanionProviderTripEntity() {
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

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return this.destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDeparture() {
        return this.departure;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss.SSS")
    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getArrival() {
        return this.arrival;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss.SSS")
    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public Boolean getIsApproved() {
        return this.isApproved;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Set<StopsEntity> getStops() {
        return this.stops;
    }

    public void setStops(Set<StopsEntity> stops) {
        this.stops = stops;
    }
}
