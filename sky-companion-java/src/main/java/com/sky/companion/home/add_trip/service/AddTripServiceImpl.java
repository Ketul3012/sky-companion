/**
 * This class implements the AddTripService interface and provides the functionality to save new trip details for a given trip request and user.
 * It interacts with the UserRepository, CompanionProviderTripRepository, and StopsRepository to store the trip details in the database.
 * The class also includes a method to fetch flight details for a given PNR code from a REST API.
 * <p>
 * This file is important to the project as it defines the implementation of the AddTripService, which is a crucial service for adding trips in the application.
 * It handles the validation of user details against the flight details fetched from the PNR code and saves the trip details in the database.
 * The class also handles the creation of stops associated with the trip, if provided.
 */

package com.sky.companion.home.add_trip.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.sky.companion.authentication.common.dto.UserDTO;
import com.sky.companion.authentication.common.entity.UserEntity;
import com.sky.companion.authentication.common.repository.UserRepository;
import com.sky.companion.common.constants.ResponseMessagesConstants;
import com.sky.companion.common.constants.ResponseStatusCodeConstants;
import com.sky.companion.common.jackson.JacksonUtils;
import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.home.add_trip.request.AddTripRequest;
import com.sky.companion.home.add_trip.request.StopsRequest;
import com.sky.companion.home.common.entity.CompanionProviderTripEntity;
import com.sky.companion.home.common.entity.StopsEntity;
import com.sky.companion.home.common.repository.CompanionProviderTripRepository;
import com.sky.companion.home.common.repository.StopsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class AddTripServiceImpl implements AddTripService {
    private static final String BASE_URL_RETOOL_API = "https://retoolapi.dev/ynt7Fy/pnr/";
    private final int minResultLength = 2;
    @Autowired
    UserRepository userRepository;

    @Autowired
    CompanionProviderTripRepository companionProviderTripRepo;

    @Autowired
    StopsRepository stopsRepository;

    /**
     * saves new trip details for the given trip request and the user.
     *
     * @param addTripRequest The request containing trip details.
     * @param userDTO        The DTO representing the user who is adding the trip.
     * @return An HTTP success response if trip details are correct as per given
     * pnr.
     */
    @Override
    public HttpSuccessResponse<String> addTrip(AddTripRequest addTripRequest, UserDTO userDTO) {

        CompanionProviderTripEntity companionProviderTripEntity = new CompanionProviderTripEntity();

        Optional<UserEntity> user = userRepository.findById(userDTO.getId());
        if (user.isPresent()) {
            JsonNode jsonObject = fetchFlightDetailsFromPnr(addTripRequest.getPnr());

            if (jsonObject != null) {
                boolean isFirstNameMatching = jsonObject.get("first_name").asText().equalsIgnoreCase(user.get().getFirstName());
                boolean isLastNameMatching = jsonObject.get("last_name").asText().equalsIgnoreCase(user.get().getLastName());
                if (!isFirstNameMatching || !isLastNameMatching) {
                    return new HttpSuccessResponse<>(ResponseStatusCodeConstants.BAD_REQUEST,
                            ResponseMessagesConstants.INVALID_PNR_DETAILS, "Invalid PNR details");
                }
                companionProviderTripEntity.setUser(user.get());
                companionProviderTripEntity.setSource(addTripRequest.getFrom());
                companionProviderTripEntity.setDestination(addTripRequest.getTo());
                companionProviderTripEntity.setArrival(addTripRequest.getArrivalTime());
                companionProviderTripEntity.setDeparture(addTripRequest.getDepartureTime());
                companionProviderTripEntity.setIsApproved(false);
                companionProviderTripEntity.setPrice(addTripRequest.getPrice());
                companionProviderTripRepo.save(companionProviderTripEntity);
                if (addTripRequest.getStops() != null) {
                    for (StopsRequest stopsRequest : addTripRequest.getStops()) {
                        StopsEntity stopsEntity = new StopsEntity();
                        stopsEntity.setName(stopsRequest.getName());
                        stopsEntity.setArrival(stopsRequest.getArrivalTime());
                        stopsEntity.setDeparture(stopsRequest.getDepartureTime());
                        stopsEntity.setTrip(companionProviderTripEntity);
                        stopsRepository.save(stopsEntity);
                    }
                }

                return new HttpSuccessResponse<>(ResponseStatusCodeConstants.SUCCESS, ResponseMessagesConstants.SUCCESS,
                        "Trip saved successfully.");
            } else {
                return new HttpSuccessResponse<>(ResponseStatusCodeConstants.BAD_REQUEST,
                        ResponseMessagesConstants.INVALID_PNR_DETAILS, "Invalid PNR details");
            }
        } else {
            return new HttpSuccessResponse<>(ResponseStatusCodeConstants.INVALID_USER,
                    ResponseMessagesConstants.INVALID_USER, "user not found");
        }
    }

    /**
     * Fetches details for the given pnr from retool api.
     *
     * @param pnrCode
     * @return json object containing details for corresponding pnr.
     */
    private JsonNode fetchFlightDetailsFromPnr(String pnrCode) {
        String apiUrl = BASE_URL_RETOOL_API + "?pnr_code=" + pnrCode;
        RestTemplate restTemplate = new RestTemplate();

        String result = restTemplate.getForObject(apiUrl, String.class);
        if (result != null && result.length() > minResultLength) {
            ArrayNode jsonArray = JacksonUtils.fromJson(result, ArrayNode.class);
            return jsonArray.get(0);
        } else {
            return null;
        }
    }

}
