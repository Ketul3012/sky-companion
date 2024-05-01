/**
 * The StopsMapper class is responsible for mapping StopsEntity objects to StopsDto objects.
 * It provides a static method, entityToDto, which takes a StopsEntity object as input and returns a corresponding StopsDto object.
 * This class is important to the project as it facilitates the conversion of data between the entity and DTO layers,
 * allowing for seamless communication and data transfer between different parts of the application.
 */

package com.sky.companion.home.common.mapper;

import com.sky.companion.home.common.dto.StopsDto;
import com.sky.companion.home.common.entity.StopsEntity;

public class StopsMapper {

    private StopsMapper() {
    }

    public static StopsDto entityToDto(StopsEntity entity) {
        StopsDto dto = new StopsDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setArrival(entity.getArrival());
        dto.setDeparture(entity.getDeparture());
        return dto;
    }
}
