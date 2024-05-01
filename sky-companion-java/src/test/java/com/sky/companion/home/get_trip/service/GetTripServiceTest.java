package com.sky.companion.home.get_trip.service;

import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.home.common.dto.CompanionProviderTripDto;
import com.sky.companion.home.common.entity.CompanionProviderTripEntity;
import com.sky.companion.home.common.repository.CompanionProviderTripRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Test case class for {@link GetTripService} using {@link Mockito}
 */
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
class GetTripServiceTest {

    @Mock
    private CompanionProviderTripRepository companionProviderTripRepo;

    @InjectMocks
    private GetTripServiceImpl getTripService;

    /**
     * this test case check that get-trip functionality working successfully.
     */
    @Test
    void testGetTripSuccess() {
        // Arrange
        Integer id = 1;
        CompanionProviderTripEntity companionProviderTripEntity = new CompanionProviderTripEntity();
        companionProviderTripEntity.setId(id);
        Mockito.when(companionProviderTripRepo.findById(id)).thenReturn(Optional.of(companionProviderTripEntity));

        // Act
        HttpSuccessResponse<CompanionProviderTripDto> response = getTripService.getTrip(id);

        // Assert
        assertNotNull(response.getData());
    }

    /**
     * this test case check get-trip functionality fails due to missing item with given id
     */
    @Test
    void testGetTripFailure() {
        // Arrange
        Integer id = 1;
        Mockito.when(companionProviderTripRepo.findById(id)).thenReturn(Optional.empty());

        // Act
        HttpSuccessResponse<CompanionProviderTripDto> response = getTripService.getTrip(id);

        // Assert
        assertNull(response.getData());
    }
}
