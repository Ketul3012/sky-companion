/**
 * The EditTripService interface defines the contract for editing a trip in the project.
 * It provides a method to edit a trip for a given ID using the {@link com.sky.companion.home.common.repository.CompanionProviderTripRepository}.
 * <p>
 * This file is important to the project as it encapsulates the logic for editing a trip and defines the necessary methods.
 * It allows other components of the project to interact with the EditTripService and perform trip editing operations.
 */

package com.sky.companion.home.edit_trip.service;

import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.home.edit_trip.request.EditTripRequest;

public interface EditTripService {

    /**
     * This method edits the trip for given id using
     * {@link com.sky.companion.home.common.repository.CompanionProviderTripRepository}
     *
     * @param requestId id of trip which user want to edit
     * @param request   changed price of the trip.
     * @return success or failure message based on action result
     */
    HttpSuccessResponse<String> editTrip(int requestId, EditTripRequest request);

}