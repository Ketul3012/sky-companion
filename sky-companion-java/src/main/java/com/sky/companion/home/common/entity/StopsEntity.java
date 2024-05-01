/**
 * This class represents a stop entity in the system.
 * It is used to store information about a stop, including its name, departure and arrival times,
 * and its association with a companion provider trip.
 * <p>
 * StopsEntity is an important file in the project as it defines the structure and behavior of a stop entity.
 * It is used to create, retrieve, update, and delete stop records in the database.
 * The stop entity is associated with a companion provider trip, allowing multiple stops to be associated with one trip.
 * <p>
 * This file is part of the sky-companion-java project, which is a system for managing companion provider trips.
 * It provides functionality to track and manage stops along a trip, allowing users to plan their journeys effectively.
 */

package com.sky.companion.home.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sky.companion.common.model.AuditModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "stops")
public class StopsEntity extends AuditModel {

    /**
     * auto generated id, used as a primary key
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * name of stop
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * departure, a time when user lands at stop
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss.SSS")
    @Column(name = "departure", nullable = false)
    private Date departure;

    /**
     * arrival, a time when user leaves the stop
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss.SSS")
    @Column(name = "arrival", nullable = false)
    private Date arrival;

    /**
     * reference to companion_provider_trip table, multiple stops can be associated
     * with one trip
     */
    @ManyToOne
    @JoinColumn(name = "trip_id", referencedColumnName = "id")
    private CompanionProviderTripEntity trip;

    public StopsEntity() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    public CompanionProviderTripEntity getTrip() {
        return this.trip;
    }

    public void setTrip(CompanionProviderTripEntity trip) {
        this.trip = trip;
    }
}
