package com.sky.companion.home.delete_trip.service;

import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.home.common.repository.CompanionProviderTripRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test case class for {@link DeleteTripService} using {@link org.mockito.Mockito}
 */
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
class DeleteTripServiceTest {

    @Mock
    private CompanionProviderTripRepository companionProviderTripRepo;

    @InjectMocks
    private DeleteTripServiceImpl deleteTripService;

    /**
     * this test case check that delete trip functionality working successfully.
     */
    @Test
    void testDeleteTripSuccess() {
        // Arrange
        Integer id = 1;
        Mockito.doNothing().when(companionProviderTripRepo).deleteById(id);

        // Act
        HttpSuccessResponse<Void> response = deleteTripService.deleteTrip(id);

        // Assert
        assertEquals("Trip deleted successfully", response.getStatusMessage());
    }

    /**
     * this test case check delete-trip functionality fails due to missing item with given id
     */
    @Test
    void testDeleteTripFailure() {
        // Arrange
        Integer id = 1;
        Mockito.doThrow(EmptyResultDataAccessException.class).when(companionProviderTripRepo).deleteById(id);

        // Act
        HttpSuccessResponse<Void> response = deleteTripService.deleteTrip(id);

        // Assert
        assertEquals("No trip with given id found", response.getStatusMessage());
    }
}
