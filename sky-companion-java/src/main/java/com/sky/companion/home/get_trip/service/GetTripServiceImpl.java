/**
 * This class is an implementation of the GetTripService interface.
 * It provides the functionality to retrieve a trip for a given ID.
 * The trips are retrieved using the CompanionProviderTripRepository.
 * <p>
 * This file is important to the project as it contains the implementation of the getTrip() method,
 * which is a crucial part of the functionality provided by the GetTripService.
 * It allows users to retrieve a specific trip by its ID.
 */

package com.sky.companion.home.get_trip.service;

import com.sky.companion.common.constants.ResponseMessagesConstants;
import com.sky.companion.common.constants.ResponseStatusCodeConstants;
import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.home.common.dto.CompanionProviderTripDto;
import com.sky.companion.home.common.entity.CompanionProviderTripEntity;
import com.sky.companion.home.common.mapper.CompanionProviderTripMapper;
import com.sky.companion.home.common.repository.CompanionProviderTripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetTripServiceImpl implements GetTripService {

    @Autowired
    private CompanionProviderTripRepository companionProviderTripRepo;

    /**
     * This method get the trip for given id using
     * {@link com.sky.companion.home.common.repository.CompanionProviderTripRepository}
     *
     * @param id id of trip which user want to get
     * @return Companion provider trip or failure message based on action result
     */
    @Override
    public HttpSuccessResponse<CompanionProviderTripDto> getTrip(Integer id) {
        Optional<CompanionProviderTripEntity> companionProviderTripEntity = companionProviderTripRepo
                .findById(id);
        if (companionProviderTripEntity.isPresent()) {
            return new HttpSuccessResponse<>(ResponseStatusCodeConstants.SUCCESS, ResponseMessagesConstants.SUCCESS,
                    CompanionProviderTripMapper.entityToDto(companionProviderTripEntity.get()));
        } else {
            return new HttpSuccessResponse<>(ResponseStatusCodeConstants.BAD_REQUEST, "No trip found with given id",
                    null);
        }
    }
}
