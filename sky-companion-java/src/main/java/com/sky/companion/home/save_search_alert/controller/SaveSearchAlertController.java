/**
 * This class is the controller for the save-search-alert endpoint.
 * It handles the incoming requests related to saving search alerts.
 * The save-search-alert endpoint allows users to save their search criteria for trips.
 * This file is important to the project as it defines the API endpoint and delegates the request to the SaveSearchAlertService.
 */

package com.sky.companion.home.save_search_alert.controller;

import com.sky.companion.authentication.common.dto.UserDTO;
import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.home.list_trips.request.ListTripsRequest;
import com.sky.companion.home.save_search_alert.service.SaveSearchAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SaveSearchAlertController {

    @Autowired
    private SaveSearchAlertService saveSearchAlertService;

    /**
     * Method to declare save-search-alert endpoint using {@link PostMapping} and
     * with request {@link ListTripsRequest}.
     * It saves the search alert based on the provided request and authenticated
     * user.
     *
     * @param request The request object containing the search criteria for trips.
     * @param user    The authenticated user making the request.
     * @return An HTTP response indicating the success or failure of saving the
     * search alert.
     */
    @PostMapping("/save-search-alert")
    public HttpSuccessResponse<String> saveSearchAlert(@Valid @RequestBody ListTripsRequest request,
                                                       @AuthenticationPrincipal UserDTO user) {
        return saveSearchAlertService.saveSearchAlertService(request, user);
    }
}
