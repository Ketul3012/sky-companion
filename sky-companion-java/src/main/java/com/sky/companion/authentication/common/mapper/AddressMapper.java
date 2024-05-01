/**
 * The AddressMapper class is responsible for mapping
 * {@link com.sky.companion.authentication.common.entity.AddressEntity} to
 * {@link com.sky.companion.authentication.common.dto.AddressDto}.
 * It provides a static method, entityToDTO, which takes an AddressEntity object
 * as input and returns
 * the corresponding AddressDto object.
 * <p>
 * This file is important to the project because it enables the conversion of
 * AddressEntity objects,
 * which represent address information in the database, to AddressDto objects,
 * which are used for
 * transferring address data between different layers of the application, such
 * as the service layer
 * and the presentation layer.
 */

package com.sky.companion.authentication.common.mapper;

import com.sky.companion.authentication.common.dto.AddressDto;
import com.sky.companion.authentication.common.entity.AddressEntity;

public class AddressMapper {

    private AddressMapper() {
    }

    /**
     * maps {@link com.sky.companion.authentication.common.entity.AddressEntity} to
     * {@link com.sky.companion.authentication.common.dto.AddressDto}
     *
     * @param entity
     * @return
     */
    public static AddressDto entityToDTO(AddressEntity entity) {
        if (entity == null) {
            return null;
        }
        AddressDto dto = new AddressDto();
        dto.setId(entity.getId());
        dto.setAddressLine1(entity.getAddressLine1());
        dto.setAddressLine2(entity.getAddressLine2());
        dto.setCity(entity.getCity());
        dto.setProvince(entity.getProvince());
        dto.setCountry(entity.getCountry());
        dto.setPostalCode(entity.getPostalCode());
        return dto;
    }
}
