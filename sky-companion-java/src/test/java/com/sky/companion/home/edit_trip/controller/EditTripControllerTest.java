package com.sky.companion.home.edit_trip.controller;

import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.home.edit_trip.request.EditTripRequest;
import com.sky.companion.home.edit_trip.service.EditTripService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * This class contains unit tests for the {@link EditTripController} class.
 */
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
class EditTripControllerTest {

    private final int statusCode = 200;
    @InjectMocks
    private EditTripController controller;
    @Mock
    private EditTripService service;

    /**
     * Test the editTrip method for a successful trip editing scenario.
     * This test simulates a situation where the trip edit is successful and
     * verifies that the response from the controller matches the expected success response.
     */
    @Test
    void testEditTripSuccess() {
        // Arrange
        int requestId = 1;
        EditTripRequest request = new EditTripRequest();

        HttpSuccessResponse<String> expectedResponse = new HttpSuccessResponse<>(statusCode, "Success", "Trip edited successfully");

        when(service.editTrip(requestId, request)).thenReturn(expectedResponse);

        // Act
        HttpSuccessResponse<String> actualResponse = controller.editTrip(requestId, request);

        // Assert
        assertEquals(expectedResponse, actualResponse);
    }

}
