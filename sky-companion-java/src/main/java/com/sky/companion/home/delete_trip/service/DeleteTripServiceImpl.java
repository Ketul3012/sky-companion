/**
 * This class implements the DeleteTripService interface and provides the functionality to delete a trip.
 * It is an important file in the project as it handles the deletion of trips based on the provided trip ID.
 */

package com.sky.companion.home.delete_trip.service;

import com.sky.companion.common.constants.ResponseStatusCodeConstants;
import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.home.common.repository.CompanionProviderTripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class DeleteTripServiceImpl implements DeleteTripService {

    @Autowired
    private CompanionProviderTripRepository companionProviderTripRepo;

    /**
     * Deletes a trip based on the provided trip ID.
     *
     * @param id the ID of the trip to be deleted
     * @return an HttpSuccessResponse indicating the success or failure of the
     * deletion operation
     */
    @Override
    public HttpSuccessResponse<Void> deleteTrip(Integer id) {
        try {
            companionProviderTripRepo.deleteById(id);
            return new HttpSuccessResponse<>(ResponseStatusCodeConstants.SUCCESS, "Trip deleted successfully", null);
        } catch (EmptyResultDataAccessException exception) {
            return new HttpSuccessResponse<>(ResponseStatusCodeConstants.BAD_REQUEST, "No trip with given id found",
                    null);
        }
    }
}
