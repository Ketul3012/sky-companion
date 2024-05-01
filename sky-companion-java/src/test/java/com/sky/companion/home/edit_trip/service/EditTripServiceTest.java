package com.sky.companion.home.edit_trip.service;

import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.home.common.entity.CompanionProviderTripEntity;
import com.sky.companion.home.common.repository.CompanionProviderTripRepository;
import com.sky.companion.home.edit_trip.controller.EditTripController;
import com.sky.companion.home.edit_trip.request.EditTripRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


/**
 * This class contains unit tests for the {@link EditTripController} class.
 */
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
class EditTripServiceTest {

    @Mock
    private CompanionProviderTripRepository companionProviderTripRepos;

    @InjectMocks
    private EditTripServiceImpl editTripService;

    @Test
    void testEditTripServiceSuccess() {
        int requestId = 1;

        EditTripRequest request = new EditTripRequest();
        CompanionProviderTripEntity companionProviderTripEntity = new CompanionProviderTripEntity();
        companionProviderTripEntity.setId(1);
        when(companionProviderTripRepos.findById(requestId)).thenReturn(Optional.of(companionProviderTripEntity));
        when(companionProviderTripRepos.save(companionProviderTripEntity)).thenReturn(companionProviderTripEntity);

        HttpSuccessResponse<String> actualResponse = editTripService.editTrip(requestId, request);
        assertEquals("Trip edited successfully", actualResponse.getStatusMessage());
    }


    @Test
    void testEditTripServiceFailure() {
        int requestId = 1;

        EditTripRequest request = new EditTripRequest();
        when(companionProviderTripRepos.findById(requestId)).thenReturn(Optional.empty());

        HttpSuccessResponse<String> actualResponse = editTripService.editTrip(requestId, request);
        assertEquals("Trip not found", actualResponse.getStatusMessage());
    }
}
