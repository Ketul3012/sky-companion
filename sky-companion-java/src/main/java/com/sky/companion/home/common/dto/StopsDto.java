/**
 * The StopsDto class represents a data transfer object for stops information.
 * It contains the id, name, departure, and arrival details of a stop.
 * This class is important to the project as it provides a standardized way to transfer stop information
 * between different components of the application.
 */

package com.sky.companion.home.common.dto;

import java.util.Date;

public class StopsDto {

    private Integer id;
    private String name;
    private Date departure;
    private Date arrival;

    public StopsDto() {
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

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getArrival() {
        return this.arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }
}
