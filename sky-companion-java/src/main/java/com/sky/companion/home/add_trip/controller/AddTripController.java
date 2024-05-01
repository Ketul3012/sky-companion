/**
 * This class represents the controller for adding a trip in the Sky Companion project.
 * It handles the HTTP requests related to adding a trip and delegates the processing to the AddTripService.
 * The add-trip endpoint is created using the PostMapping annotation and accepts a request body of type AddTripRequest
 * and the currently logged-in user information as UserDTO.
 * The addTrip method is responsible for validating the request, calling the addTripService to add the trip to the database,
 * and returning the status message with the review written by the user.
 * <p>
 * This file is important to the project as it provides the API endpoint for adding a trip and connects the client-side
 * requests to the backend service for processing and storing the trip information.
 */

package com.sky.companion.home.add_trip.controller;

import com.sky.companion.authentication.common.dto.UserDTO;
import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.home.add_trip.request.AddTripRequest;
import com.sky.companion.home.add_trip.service.AddTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AddTripController {
    @Autowired
    AddTripService addTripService;

    /**
     * Method to declare the add-trip endpoint created using PostMapping to take
     * request and pass it to the service.
     *
     * @param addTripRequest object contains fields required for storing companion
     *                       provider trip to the database.
     * @param userDTO        object is for the currently logged-in user who is
     *                       creating the trip.
     * @return the status message with the review written by the user.
     */
    @PostMapping("/add-trip")
    public HttpSuccessResponse<String> addTrip(@Valid @RequestBody AddTripRequest addTripRequest,
                                               @AuthenticationPrincipal UserDTO userDTO) {
        return addTripService.addTrip(addTripRequest, userDTO);
    }
}
