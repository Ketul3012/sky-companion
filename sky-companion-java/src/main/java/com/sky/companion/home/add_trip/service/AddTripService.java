/**
 * The AddTripService interface provides functionality for adding trips.
 * It defines the addTrip method, which allows users to add trips by providing trip details and user information.
 * This interface is important to the project as it serves as a contract for implementing the addTrip functionality.
 * It ensures that all classes implementing this interface provide the necessary functionality to add trips.
 */

package com.sky.companion.home.add_trip.service;

import com.sky.companion.authentication.common.dto.UserDTO;
import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.home.add_trip.request.AddTripRequest;

public interface AddTripService {
    /**
     * Adds a trip based on the provided trip details and user information.
     *
     * @param addTripRequest The request containing trip details.
     * @param userDTO        The DTO representing the user who is adding the trip.
     * @return An HTTP success response if the trip details are correct as per the
     * given PNR.
     */
    HttpSuccessResponse<String> addTrip(AddTripRequest addTripRequest, UserDTO userDTO);
}
