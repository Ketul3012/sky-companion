/**
 * The DeleteTripService interface provides methods to delete a trip for a given ID.
 * It is an important part of the project as it defines the contract for deleting trips
 * and provides a way to interact with the {@link com.sky.companion.home.common.repository.CompanionProviderTripRepository}.
 */

package com.sky.companion.home.delete_trip.service;

import com.sky.companion.common.model.HttpSuccessResponse;

public interface DeleteTripService {

    /**
     * This method delete the trip for given id using
     * {@link com.sky.companion.home.common.repository.CompanionProviderTripRepository}
     *
     * @param id id of trip which user want to delete
     * @return success or failure message based on action result
     */
    HttpSuccessResponse<Void> deleteTrip(Integer id);

}
