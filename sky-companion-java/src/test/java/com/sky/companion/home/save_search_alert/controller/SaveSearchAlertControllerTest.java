package com.sky.companion.home.save_search_alert.controller;

import com.sky.companion.authentication.common.dto.UserDTO;
import com.sky.companion.common.constants.ResponseMessagesConstants;
import com.sky.companion.common.constants.ResponseStatusCodeConstants;
import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.home.list_trips.request.ListTripsRequest;
import com.sky.companion.home.list_trips.response.ListTripsResponse;
import com.sky.companion.home.save_search_alert.service.SaveSearchAlertService;
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
 * Test case class for {@link SaveSearchAlertController} using {@link Mockito}
 */
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
class SaveSearchAlertControllerTest {

    final private static List<Integer> languageList = Arrays.asList(1, 2, 3);
    final Float maximumCost = 500f;
    final int count = 0;
    @Mock
    private SaveSearchAlertService saveSearchAlertService;
    @InjectMocks
    private SaveSearchAlertController saveSearchAlertController;

    /**
     * test case for save-search-alert endpoint
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
        listTripsResponse.setCount(count);
        listTripsResponse.setTrips(new ArrayList<>());
        HttpSuccessResponse<String> saveSearchAlertResponse = new HttpSuccessResponse<>(ResponseStatusCodeConstants.SUCCESS, ResponseMessagesConstants.SUCCESS, "Search alert saved successfully");
        UserDTO userDTO = new UserDTO();
        Mockito.when(saveSearchAlertService.saveSearchAlertService(listTripsRequest, userDTO)).thenReturn(saveSearchAlertResponse);

        // Act
        HttpSuccessResponse<String> response = saveSearchAlertController.saveSearchAlert(listTripsRequest, userDTO);

        // Assert
        assertEquals(ResponseStatusCodeConstants.SUCCESS, response.getStatusCode());
    }
}
