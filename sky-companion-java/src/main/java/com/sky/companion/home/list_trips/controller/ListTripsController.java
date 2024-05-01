/**
 * This class represents the controller for the list-trips endpoint in the Sky Companion project.
 * It is responsible for handling incoming HTTP requests related to listing trips and delegating the processing to the {@link ListTripsService}.
 * The list-trips endpoint allows users to retrieve a list of trips based on certain criteria.
 * <p>
 * This file is important to the project as it defines the API endpoint and maps it to the corresponding service method.
 * It plays a crucial role in the overall functionality of the Sky Companion application by providing the necessary interface for listing trips.
 */

package com.sky.companion.home.list_trips.controller;

import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.home.list_trips.request.ListTripsRequest;
import com.sky.companion.home.list_trips.response.ListTripsResponse;
import com.sky.companion.home.list_trips.service.ListTripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ListTripsController {

    @Autowired
    private ListTripsService listTripsService;

    /**
     * Method to declare the list-trips endpoint using {@link PostMapping} and with
     * request {@link ListTripsRequest}.
     *
     * @param request the request object containing the criteria for listing trips
     * @return an HTTP success response containing the list of trips that match the
     * criteria
     */
    @PostMapping("/list-trips")
    public HttpSuccessResponse<ListTripsResponse> listTrips(@Valid @RequestBody ListTripsRequest request) {
        return listTripsService.listTrips(request);
    }
}
