/**
 * The CompanionProviderTripDto class represents a data transfer object for a companion provider trip.
 * It contains information about the trip, such as the trip ID, source, destination, departure and arrival dates,
 * approval status, user ID, user details, price, stops, and timestamps for creation and last update.
 * <p>
 * This file is important to the project as it serves as a data structure for transferring companion provider trip information
 * between different layers of the application, such as the service layer and the presentation layer.
 * It allows for easy encapsulation and transportation of trip data, facilitating the implementation of various functionalities
 * related to companion provider trips in the application.
 */

package com.sky.companion.home.common.dto;

import com.sky.companion.authentication.common.dto.UserDTO;

import java.util.Date;
import java.util.List;

public class CompanionProviderTripDto {

    private Integer id;
    private String source;
    private String destination;
    private Date departure;
    private Date arrival;
    private Boolean isApproved;
    private Integer userId;
    private UserDTO user;
    private Float price;
    private List<StopsDto> stops;
    private Date createdStamp;
    private Date lastUpdatedStamp;

    public CompanionProviderTripDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public List<StopsDto> getStops() {
        return stops;
    }

    public void setStops(List<StopsDto> stops) {
        this.stops = stops;
    }

    public Date getCreatedStamp() {
        return createdStamp;
    }

    public void setCreatedStamp(Date createdStamp) {
        this.createdStamp = createdStamp;
    }

    public Date getLastUpdatedStamp() {
        return lastUpdatedStamp;
    }

    public void setLastUpdatedStamp(Date lastUpdatedStamp) {
        this.lastUpdatedStamp = lastUpdatedStamp;
    }
}
