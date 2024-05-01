/**
 * This class is an implementation of the EditTripService interface.
 * It provides the functionality to edit a trip in the companion provider system.
 * The EditTripServiceImpl class is an important part of the project as it handles the logic for editing trips.
 * It retrieves the trip with the given ID from the companionProviderTripRepository,
 * updates the price of the trip with the price provided in the EditTripRequest,
 * and saves the updated trip back to the repository.
 * If the trip is not found, it returns an error response indicating that the trip was not found.
 */

package com.sky.companion.home.edit_trip.service;

import com.sky.companion.common.constants.ResponseStatusCodeConstants;
import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.home.common.entity.CompanionProviderTripEntity;
import com.sky.companion.home.common.repository.CompanionProviderTripRepository;
import com.sky.companion.home.edit_trip.request.EditTripRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EditTripServiceImpl implements EditTripService {

    @Autowired
    private CompanionProviderTripRepository companionProviderTripRepo;

    /**
     * {@inheritDoc}
     *
     * @param requestId id of trip which user want to edit
     * @param request   edit trip request with trip.
     * @return success or failure message based on action result
     */
    @Override
    public HttpSuccessResponse<String> editTrip(int requestId, EditTripRequest request) {
        Optional<CompanionProviderTripEntity> trip = companionProviderTripRepo.findById(requestId);
        if (trip.isPresent()) {
            trip.get().setPrice(request.getPrice());
            companionProviderTripRepo.save(trip.get());
            return new HttpSuccessResponse<>(ResponseStatusCodeConstants.SUCCESS, "Trip edited successfully", null);
        } else {
            return new HttpSuccessResponse<>(ResponseStatusCodeConstants.INVALID_TRIP, "Trip not found", null);
        }
    }
}