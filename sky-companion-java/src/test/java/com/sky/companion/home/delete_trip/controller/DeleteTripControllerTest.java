package com.sky.companion.home.delete_trip.controller;

import com.sky.companion.common.constants.ResponseStatusCodeConstants;
import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.home.delete_trip.service.DeleteTripService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test case class for {@link DeleteTripController} using {@link org.mockito.Mockito}
 */
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
class DeleteTripControllerTest {

    @Mock
    private DeleteTripService deleteTripService;

    @InjectMocks
    private DeleteTripController deleteTripController;


    /**
     * this method test delete-trip endpoint
     */
    @Test
    void testDeleteTripEndpoint() {
        // Arrange
        Integer id = 1;
        Mockito.when(deleteTripService.deleteTrip(id)).thenReturn(new HttpSuccessResponse<>(ResponseStatusCodeConstants.SUCCESS, "Trip deleted successfully", null));

        // Act
        HttpSuccessResponse<Void> response = deleteTripController.deleteTrip(id);

        // Assert
        assertEquals("Trip deleted successfully", response.getStatusMessage());
    }

}
