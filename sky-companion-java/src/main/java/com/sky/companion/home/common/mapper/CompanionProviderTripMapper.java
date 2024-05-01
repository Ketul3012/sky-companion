/**
 * The CompanionProviderTripMapper class is responsible for mapping between CompanionProviderTripEntity and CompanionProviderTripDto objects.
 * It provides a static method, entityToDto, which takes a CompanionProviderTripEntity object as input and returns a corresponding CompanionProviderTripDto object.
 * This class is important to the project as it facilitates the conversion of data between the entity and DTO layers, allowing for seamless communication and data transfer.
 */

package com.sky.companion.home.common.mapper;

import com.sky.companion.authentication.common.mapper.UserMapper;
import com.sky.companion.home.common.dto.CompanionProviderTripDto;
import com.sky.companion.home.common.entity.CompanionProviderTripEntity;

import java.util.stream.Collectors;

public class CompanionProviderTripMapper {

    private CompanionProviderTripMapper() {
    }

    public static CompanionProviderTripDto entityToDto(CompanionProviderTripEntity entity) {
        CompanionProviderTripDto dto = new CompanionProviderTripDto();
        dto.setId(entity.getId());
        dto.setDeparture(entity.getDeparture());
        dto.setArrival(entity.getArrival());
        dto.setSource(entity.getSource());
        dto.setDestination(entity.getDestination());
        dto.setUserId(entity.getUser() != null ? entity.getUser().getId() : null);
        dto.setPrice(entity.getPrice());
        dto.setUser(entity.getUser() != null ? UserMapper.entityToDTO(entity.getUser()) : null);
        dto.setStops(entity.getStops() != null
                ? entity.getStops().stream().map(StopsMapper::entityToDto).collect(Collectors.toList())
                : null);
        dto.setApproved(entity.getIsApproved());
        dto.setCreatedStamp(entity.getCreatedStamp());
        dto.setLastUpdatedStamp(entity.getLastUpdatedStamp());
        return dto;
    }
}
