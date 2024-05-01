package com.sky.companion.authentication.common.mapper;

import com.sky.companion.authentication.common.dto.AddressDto;
import com.sky.companion.authentication.common.entity.AddressEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AddressMapperTest {

    @Test
    void testEntityToDTOMapping() {
        // Arrange
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(1);
        addressEntity.setAddressLine1("123 Main St");
        addressEntity.setAddressLine2("Apt 456");
        addressEntity.setCity("City");
        addressEntity.setProvince("Province");
        addressEntity.setCountry("Country");
        addressEntity.setPostalCode("12345");

        AddressDto addressDto = new AddressDto();
        addressDto.setId(1);
        addressDto.setAddressLine1("123 Main St");
        addressDto.setAddressLine2("Apt 456");
        addressDto.setCity("City");
        addressDto.setProvince("Province");
        addressDto.setCountry("Country");
        addressDto.setPostalCode("12345");

        // Act and Assert
        assertThat(addressDto).usingRecursiveComparison().isEqualTo(AddressMapper.entityToDTO(addressEntity));

    }
}
