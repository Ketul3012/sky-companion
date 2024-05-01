package com.sky.companion.home.common.mapper;

import com.sky.companion.authentication.common.dto.UserDTO;
import com.sky.companion.authentication.common.entity.UserEntity;
import com.sky.companion.home.common.dto.CompanionProviderTripDto;
import com.sky.companion.home.common.dto.StopsDto;
import com.sky.companion.home.common.entity.CompanionProviderTripEntity;
import com.sky.companion.home.common.entity.StopsEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test case class for {@link com.sky.companion.home.common.mapper.CompanionProviderTripMapper} using {@link org.mockito.Mockito}
 */
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
class CompanionProviderTripMapperTest {
    private static final Date DATE = new Date(System.currentTimeMillis());
    private static final Float price = 100.f;

    private static CompanionProviderTripEntity getCompanionProviderTripEntity() {
        CompanionProviderTripEntity companionProviderTripEntity = new CompanionProviderTripEntity();
        companionProviderTripEntity.setId(1);
        companionProviderTripEntity.setSource("test");
        companionProviderTripEntity.setDestination("test");
        companionProviderTripEntity.setArrival(DATE);
        companionProviderTripEntity.setDeparture(DATE);
        companionProviderTripEntity.setIsApproved(Boolean.TRUE);
        companionProviderTripEntity.setPrice(price);
        companionProviderTripEntity.setUser(new UserEntity());
        Set<StopsEntity> stopsEntities = new HashSet<>();
        StopsEntity stopsEntity = new StopsEntity();
        stopsEntity.setTrip(companionProviderTripEntity);
        stopsEntity.setId(1);
        stopsEntity.setArrival(DATE);
        stopsEntity.setDeparture(DATE);
        stopsEntity.setName("test");
        stopsEntities.add(stopsEntity);
        companionProviderTripEntity.setStops(stopsEntities);
        return companionProviderTripEntity;
    }

    private static CompanionProviderTripDto getCompanionProviderTripDto() {
        CompanionProviderTripDto companionProviderTripDto = new CompanionProviderTripDto();
        companionProviderTripDto.setId(1);
        companionProviderTripDto.setSource("test");
        companionProviderTripDto.setDestination("test");
        companionProviderTripDto.setArrival(DATE);
        companionProviderTripDto.setDeparture(DATE);
        companionProviderTripDto.setApproved(Boolean.TRUE);
        companionProviderTripDto.setPrice(price);
        companionProviderTripDto.setUser(new UserDTO());
        companionProviderTripDto.setUserId(null);
        List<StopsDto> stopsDtos = new ArrayList<>();
        StopsDto stopsDto = new StopsDto();
        stopsDto.setId(1);
        stopsDto.setArrival(DATE);
        stopsDto.setDeparture(DATE);
        stopsDto.setName("test");
        stopsDtos.add(stopsDto);
        companionProviderTripDto.setStops(stopsDtos);
        return companionProviderTripDto;
    }

    /**
     * Test method to check entity to dto conversion working successfully using {@link CompanionProviderTripMapper}
     */
    @Test
    void testCompanionTripEntityToDtoMappingWithUserAndStops() {

        // Arrange
        CompanionProviderTripEntity companionProviderTripEntity = getCompanionProviderTripEntity();

        CompanionProviderTripDto companionProviderTripDto = getCompanionProviderTripDto();

        // Act and Assert
        assertThat(companionProviderTripDto).usingRecursiveComparison().ignoringFieldsOfTypes(UserDTO.class).isEqualTo(CompanionProviderTripMapper.entityToDto(companionProviderTripEntity));
    }

    /**
     * Test method to check entity to dto conversion working successfully using {@link CompanionProviderTripMapper}
     */
    @Test
    void testCompanionTripEntityToDtoMappingWithoutUserAndStops() {

        // Arrange
        CompanionProviderTripEntity companionProviderTripEntity = getCompanionProviderTripEntity();
        companionProviderTripEntity.setUser(null);
        companionProviderTripEntity.setStops(null);

        CompanionProviderTripDto companionProviderTripDto = getCompanionProviderTripDto();
        companionProviderTripDto.setUser(null);
        companionProviderTripDto.setUserId(null);
        companionProviderTripDto.setStops(null);

        // Act and Assert
        assertThat(companionProviderTripDto).usingRecursiveComparison().ignoringFieldsOfTypes(UserDTO.class).isEqualTo(CompanionProviderTripMapper.entityToDto(companionProviderTripEntity));
    }
}
