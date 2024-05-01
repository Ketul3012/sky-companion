/**
 * This class is a controller that handles the delete-trip endpoint in the Sky Companion project.
 * It is responsible for receiving HTTP requests to delete a trip and invoking the appropriate service method.
 * The delete-trip endpoint allows users to delete a trip by providing the trip ID.
 * <p>
 * This class is important to the project as it provides the functionality to delete trips, which is a core feature of the application.
 * By deleting a trip, users can manage their travel plans and remove unwanted trips from their itinerary.
 * <p>
 * The delete-trip endpoint is implemented using the HTTP DELETE method and expects the trip ID as a path variable.
 * The deleteTrip method invokes the deleteTrip method of the DeleteTripService class to perform the actual deletion.
 * The method returns an HttpSuccessResponse object indicating the success or failure of the deletion operation.
 */

package com.sky.companion.home.delete_trip.controller;

import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.home.delete_trip.service.DeleteTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteTripController {

    @Autowired
    private DeleteTripService deleteTripService;

    /**
     * Method to handle the delete-trip endpoint.
     * Deletes a trip based on the provided trip ID.
     *
     * @param id the ID of the trip to be deleted
     * @return an HttpSuccessResponse indicating the success or failure of the
     * deletion operation
     */
    @DeleteMapping("/delete-trip/{id}")
    HttpSuccessResponse<Void> deleteTrip(@PathVariable(value = "id") Integer id) {
        return deleteTripService.deleteTrip(id);
    }

}
