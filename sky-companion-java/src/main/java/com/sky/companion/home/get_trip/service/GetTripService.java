/**
 * The GetTripService interface is responsible for retrieving a trip for a given ID.
 * It utilizes the {@link com.sky.companion.home.common.repository.CompanionProviderTripRepository} to fetch the trip.
 * <p>
 * This interface defines a single method, getTrip, which takes an ID as input and returns a response containing
 * the companion provider trip or a failure message based on the result of the action.
 * <p>
 * This file is important to the project as it provides the contract for retrieving trips and serves as an abstraction
 * layer between the application logic and the data access layer.
 */

package com.sky.companion.home.get_trip.service;

import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.home.common.dto.CompanionProviderTripDto;

public interface GetTripService {

    /**
     * This method get the trip for given id using
     * {@link com.sky.companion.home.common.repository.CompanionProviderTripRepository}
     *
     * @param id id of trip which user want to get
     * @return Companion provider trip or failure message based on action result
     */
    HttpSuccessResponse<CompanionProviderTripDto> getTrip(Integer id);

}
