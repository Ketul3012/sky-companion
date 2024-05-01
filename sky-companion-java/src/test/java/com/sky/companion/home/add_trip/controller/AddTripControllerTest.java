package com.sky.companion.home.add_trip.controller;

import com.sky.companion.authentication.common.dto.UserDTO;
import com.sky.companion.common.constants.ResponseMessagesConstants;
import com.sky.companion.common.constants.ResponseStatusCodeConstants;
import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.home.add_trip.request.AddTripRequest;
import com.sky.companion.home.add_trip.service.AddTripService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class AddTripControllerTest {
    private final Validator validator = new LocalValidatorFactoryBean();
    @InjectMocks
    private AddTripController addTripController;
    @Mock
    private AddTripService addTripService;
    @Mock
    private UserDTO mockUserDTO;

    @Test
    public void testAddTrip() {
        AddTripRequest addTripRequest = new AddTripRequest();

        HttpSuccessResponse<String> mockResponse = new HttpSuccessResponse<>(ResponseStatusCodeConstants.SUCCESS, ResponseMessagesConstants.SUCCESS, "Trip saved successfully.");
        when(addTripService.addTrip(any(AddTripRequest.class), any(UserDTO.class))).thenReturn(mockResponse);

        HttpSuccessResponse<String> response = addTripController.addTrip(addTripRequest, mockUserDTO);

        verify(addTripService, times(1)).addTrip(addTripRequest, mockUserDTO);

        assertEquals(mockResponse, response);
    }
}
