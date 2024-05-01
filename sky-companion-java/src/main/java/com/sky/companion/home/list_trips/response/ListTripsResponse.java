/**
 * The ListTripsResponse class represents the response object for listing trips in the project.
 * It contains a list of CompanionProviderTripDto objects and the count of trips.
 * This class is important as it provides the necessary data structure to hold and transfer the list of trips and their count.
 */

package com.sky.companion.home.list_trips.response;

import com.sky.companion.home.common.dto.CompanionProviderTripDto;

import java.util.List;

public class ListTripsResponse {

    private List<CompanionProviderTripDto> trips;

    private Integer count;

    public ListTripsResponse() {
    }

    public List<CompanionProviderTripDto> getTrips() {
        return this.trips;
    }

    public void setTrips(List<CompanionProviderTripDto> trips) {
        this.trips = trips;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
