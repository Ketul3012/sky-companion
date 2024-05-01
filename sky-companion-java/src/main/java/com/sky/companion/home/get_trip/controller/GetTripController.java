/**
 * This class is a controller that handles the "get-trip" endpoint.
 * It is responsible for fetching a trip based on the provided ID.
 * The controller uses the GetTripService to retrieve the trip information.
 * <p>
 * This file is important to the project as it provides the API endpoint for retrieving a trip.
 * It allows clients to request trip information by sending a GET request to the "/get-trip/{id}" URL.
 * The controller delegates the actual retrieval of the trip to the GetTripService.
 */

package com.sky.companion.home.get_trip.controller;

import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.home.common.dto.CompanionProviderTripDto;
import com.sky.companion.home.get_trip.service.GetTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Get trip endpoint rest controller
 */
@RestController
public class GetTripController {

    @Autowired
    private GetTripService getTripService;

    /**
     * method to declare get-trip endpoint using
     * {@link org.springframework.web.bind.annotation.DeleteMapping}
     *
     * @param id id for which user want to fetch trip
     * @return success or failure based on action result
     */
    @GetMapping("/get-trip/{id}")
    HttpSuccessResponse<CompanionProviderTripDto> getTrip(@PathVariable(value = "id") Integer id) {
        return getTripService.getTrip(id);
    }

}
