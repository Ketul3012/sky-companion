/**
 * The SaveSearchAlertService interface defines the contract for saving search alerts in the application.
 * It provides methods to save a search alert based on a given list of trips and user information.
 * This interface is an important part of the project as it allows users to create and manage search alerts
 * for specific trips of interest.
 */

package com.sky.companion.home.save_search_alert.service;

import com.sky.companion.authentication.common.dto.UserDTO;
import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.home.list_trips.request.ListTripsRequest;

public interface SaveSearchAlertService {

    /**
     * this method takes {@link  ListTripsRequest} as request to save that as search alert
     *
     * @param listTripsRequest request for which user want to create search alert
     * @param user             user object of logged in user injected through spring security
     * @return success or failure based on search alert creation
     */
    HttpSuccessResponse<String> saveSearchAlertService(ListTripsRequest listTripsRequest, UserDTO user);

    /**
     * Synchronizes posts with search alerts.
     * This method is responsible for updating the search alerts based on new posts that match the search criteria.
     * It ensures that users receive up-to-date notifications for relevant posts.
     */
    void syncPostsWithSearchAlerts();

}
