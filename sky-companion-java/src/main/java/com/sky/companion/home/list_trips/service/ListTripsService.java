/**
 * The ListTripsService interface defines the contract for retrieving a list of trips based on given filters and search criteria.
 * It provides a method, listTrips, which takes a ListTripsRequest as input and returns a list of trips that match the specified criteria.
 * The implementation of this interface is responsible for querying the database using the CompanionProviderTripRepository to fetch the available trips.
 * <p>
 * This file is important to the project as it serves as the service layer for retrieving trips. It encapsulates the business logic for filtering and searching trips,
 * and provides a standardized interface for other components to interact with the list of trips functionality.
 */

package com.sky.companion.home.list_trips.service;

import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.home.list_trips.request.ListTripsRequest;
import com.sky.companion.home.list_trips.response.ListTripsResponse;

public interface ListTripsService {

    /**
     * this method takes {@link ListTripsRequest} as request do search and filter
     * based on request and give out all available trips from database using
     * {@link com.sky.companion.home.common.repository.CompanionProviderTripRepository}
     *
     * @param listTripsRequest
     * @return list of trips based on given filters and search criteria
     */
    HttpSuccessResponse<ListTripsResponse> listTrips(ListTripsRequest listTripsRequest);

}
