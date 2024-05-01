package com.sky.companion.home.get_trip.controller;

import com.sky.companion.common.constants.ResponseMessagesConstants;
import com.sky.companion.common.constants.ResponseStatusCodeConstants;
import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.home.common.dto.CompanionProviderTripDto;
import com.sky.companion.home.get_trip.service.GetTripService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test case class for {@link GetTripController} using {@link Mockito}
 */
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
class GetTripControllerTest {

    @Mock
    private GetTripService getTripService;

    @InjectMocks
    private GetTripController getTripController;


    /**
     * this method test get-trip endpoint
     */
    @Test
    void testDeleteTripEndpoint() {
        // Arrange
        Integer id = 1;
        CompanionProviderTripDto companionProviderTripDto = new CompanionProviderTripDto();
        Mockito.when(getTripService.getTrip(id)).thenReturn(new HttpSuccessResponse<>(ResponseStatusCodeConstants.SUCCESS, ResponseMessagesConstants.SUCCESS, companionProviderTripDto));

        // Act
        HttpSuccessResponse<CompanionProviderTripDto> response = getTripController.getTrip(id);

        // Assert
        assertNotNull(response.getData());
    }

}
