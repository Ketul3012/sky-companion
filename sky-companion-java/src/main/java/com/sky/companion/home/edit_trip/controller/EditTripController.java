/**
 * The EditTripController class is responsible for handling HTTP requests related to editing a trip.
 * It is an important part of the project as it provides the necessary endpoints for clients to update trip information.
 */

package com.sky.companion.home.edit_trip.controller;

import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.home.edit_trip.request.EditTripRequest;
import com.sky.companion.home.edit_trip.service.EditTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EditTripController {

    @Autowired
    private EditTripService editTripService;

    /**
     * Handles the HTTP POST request for editing a trip.
     *
     * @param requestId The ID of the trip request to be edited.
     * @param request   The EditTripRequest object containing the updated trip
     *                  information.
     * @return An HttpSuccessResponse object with a success message indicating the
     * trip was successfully edited.
     */
    @PostMapping("/edit-trip/{requestId}")
    public HttpSuccessResponse<String> editTrip(@PathVariable("requestId") int requestId,
                                                @RequestBody EditTripRequest request) {
        return editTripService.editTrip(requestId, request);
    }
}
