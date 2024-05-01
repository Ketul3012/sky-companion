package com.sky.companion.home.list_trips.controller;

import com.sky.companion.common.constants.ResponseMessagesConstants;
import com.sky.companion.common.constants.ResponseStatusCodeConstants;
import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.home.list_trips.request.ListTripsRequest;
import com.sky.companion.home.list_trips.response.ListTripsResponse;
import com.sky.companion.home.list_trips.service.ListTripsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test case class for {@link com.sky.companion.home.list_trips.controller.ListTripsController} using {@link org.mockito.Mockito}
 */
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
class ListTripsControllerTest {
    final private static List<Integer> languageList = Arrays.asList(1, 2, 3);
    private final Float maximumCost = 500f;
    @Mock
    private ListTripsService listTripsService;

    @InjectMocks
    private ListTripsController listTripsController;


    /**
     * test case for list-trips endpoint
     *
     * @throws Exception
     */
    @Test
    void testListTripsEndpoint() {

        // Arrange
        ListTripsRequest listTripsRequest = new ListTripsRequest();
        Date date = Mockito.mock(Date.class);
        String from = "Halifax";
        String to = "Halifax";
        String user = "halifax";
        String companionCity = "Halifax";
        List<Integer> languageIds = languageList;
        listTripsRequest.setTo(to);
        listTripsRequest.setFrom(from);
        listTripsRequest.setUser(user);
        listTripsRequest.setLanguageIds(languageIds);
        listTripsRequest.setMaximumCost(maximumCost);
        listTripsRequest.setCompanionCity(companionCity);
        listTripsRequest.setDate(date);

        ListTripsResponse listTripsResponse = new ListTripsResponse();
        listTripsResponse.setCount(0);
        listTripsResponse.setTrips(new ArrayList<>());
        HttpSuccessResponse<ListTripsResponse> listTripsHttpSuccessResponse = new HttpSuccessResponse<>(ResponseStatusCodeConstants.SUCCESS, ResponseMessagesConstants.SUCCESS, listTripsResponse);

        Mockito.when(listTripsService.listTrips(listTripsRequest)).thenReturn(listTripsHttpSuccessResponse);

        // Act
        HttpSuccessResponse<ListTripsResponse> response = listTripsController.listTrips(listTripsRequest);

        // Assert
        assertEquals(ResponseStatusCodeConstants.SUCCESS, response.getStatusCode());
    }
}
