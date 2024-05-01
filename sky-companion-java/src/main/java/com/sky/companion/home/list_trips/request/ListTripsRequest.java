/**
 * The ListTripsRequest class represents a request object used for listing trips.
 * It contains various properties such as 'from', 'to', 'date', 'user', 'companionCity',
 * 'maximumCost', and 'languageIds' that are used to filter and retrieve trips based on
 * specific criteria.
 * <p>
 * This file is important to the project as it defines the structure of the request object
 * used for listing trips. It provides a standardized way to pass the necessary information
 * to the backend server for retrieving relevant trips. By using this class, the application
 * can ensure consistency and maintainability in handling trip listing requests.
 */

package com.sky.companion.home.list_trips.request;

import java.util.Date;
import java.util.List;

public class ListTripsRequest {

    private String from;

    private String to;

    private Date date;

    private String user;

    private String companionCity;

    private Float maximumCost;

    private List<Integer> languageIds;

    public ListTripsRequest() {
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCompanionCity() {
        return this.companionCity;
    }

    public void setCompanionCity(String companionCity) {
        this.companionCity = companionCity;
    }

    public Float getMaximumCost() {
        return this.maximumCost;
    }

    public void setMaximumCost(Float maximumCost) {
        this.maximumCost = maximumCost;
    }

    public List<Integer> getLanguageIds() {
        return this.languageIds;
    }

    public void setLanguageIds(List<Integer> languageIds) {
        this.languageIds = languageIds;
    }
}
