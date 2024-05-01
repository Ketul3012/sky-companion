package com.sky.companion.home.save_search_alert.service;

import com.sky.companion.authentication.common.dto.UserDTO;
import com.sky.companion.authentication.common.entity.UserEntity;
import com.sky.companion.authentication.common.repository.UserRepository;
import com.sky.companion.common.constants.ResponseMessagesConstants;
import com.sky.companion.common.constants.ResponseStatusCodeConstants;
import com.sky.companion.common.jackson.JacksonUtils;
import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.home.common.entity.SearchAlertsEntity;
import com.sky.companion.home.common.repository.SearchAlertsRepository;
import com.sky.companion.home.list_trips.request.ListTripsRequest;
import com.sky.companion.home.list_trips.response.ListTripsResponse;
import com.sky.companion.home.list_trips.service.ListTripsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Test case class for {@link SaveSearchAlertService} using {@link org.mockito.Mockito}
 */
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
class SaveSearchAlertServiceTest {
    final private static List<Integer> languageList = Arrays.asList(1, 2, 3);
    private final Float maximumCost = 100.0f;
    private final int searchAlertEntity1Id = 1;
    private final int searchAlertEntity2Id = 2;
    private final int count = 1;
    private final int wantedNumberofInvocations = 2;

    @Mock
    private SearchAlertsRepository searchAlertsRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ListTripsService listTripsService;

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private SaveSearchAlertServiceImpl saveSearchAlertService;


    /**
     * test case for save search alert success case
     */
    @Test
    void testSaveSearchAlertSuccess() {
        // Arrange
        ListTripsRequest listTripsRequest = new ListTripsRequest();
        listTripsRequest.setFrom("Halifax");
        listTripsRequest.setTo("Ahmedabad");

        UserDTO user = new UserDTO();
        user.setId(1);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(userEntity));
        when(searchAlertsRepository.save(any(SearchAlertsEntity.class))).thenReturn(any(SearchAlertsEntity.class));

        // Act
        HttpSuccessResponse<String> saveSearchAlertResponse = saveSearchAlertService.saveSearchAlertService(listTripsRequest, user);

        // Assert
        verify(userRepository, times(1)).findById(user.getId());
        verify(searchAlertsRepository, times(1)).save(any(SearchAlertsEntity.class));
        assertEquals("Search alert saved successfully", saveSearchAlertResponse.getData());
    }


    /**
     * test case for save search alert failure case
     */
    @Test
    void testSaveSearchAlertFailureUserEntityNotPresent() {
        // Arrange
        ListTripsRequest listTripsRequest = new ListTripsRequest();
        listTripsRequest.setFrom("Halifax");
        listTripsRequest.setTo("Ahmedabad");

        UserDTO user = new UserDTO();
        user.setId(1);

        SearchAlertsEntity searchAlertsEntity = new SearchAlertsEntity();
        searchAlertsEntity.setUser(null);
        searchAlertsEntity.setSearchJson(JacksonUtils.toJson(listTripsRequest));

        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        // Act
        HttpSuccessResponse<String> saveSearchAlertResponse = saveSearchAlertService.saveSearchAlertService(listTripsRequest, user);

        // Assert
        verify(userRepository, times(1)).findById(user.getId());
        verify(searchAlertsRepository, times(0)).save(searchAlertsEntity);
        assertEquals("Invalid user session", saveSearchAlertResponse.getData());
    }

    /**
     * test case for save search alert failure case
     */
    @Test
    void testSaveSearchAlertFailure() {
        // Arrange
        ListTripsRequest listTripsRequest = new ListTripsRequest();
        listTripsRequest.setFrom("Halifax");
        listTripsRequest.setTo("Ahmedabad");

        SearchAlertsEntity searchAlertsEntity = new SearchAlertsEntity();
        searchAlertsEntity.setUser(null);
        searchAlertsEntity.setSearchJson(JacksonUtils.toJson(listTripsRequest));

        // Act
        HttpSuccessResponse<String> saveSearchAlertResponse = saveSearchAlertService.saveSearchAlertService(listTripsRequest, null);

        // Assert
        verify(userRepository, times(0)).findById(1);
        verify(searchAlertsRepository, times(0)).save(searchAlertsEntity);
        assertEquals("Invalid user session", saveSearchAlertResponse.getData());
    }


    /**
     * test case to validate that sync posts functionality work as expected
     */
    @Test
    void testSyncPostsWithSearchAlerts() {

        // Arrange
        SearchAlertsEntity searchAlertsEntity1 = new SearchAlertsEntity();
        SearchAlertsEntity searchAlertsEntity2 = new SearchAlertsEntity();
        ListTripsRequest listTripsRequest = new ListTripsRequest();
        listTripsRequest.setFrom("Halifax");
        listTripsRequest.setTo("Ahmedabad");
        listTripsRequest.setMaximumCost(maximumCost);
        listTripsRequest.setUser("Test");
        listTripsRequest.setLanguageIds(languageList);
        listTripsRequest.setCompanionCity("Ahmedabad");
        listTripsRequest.setDate(Date.from(Instant.now()));
        UserEntity userEntity1 = new UserEntity();
        UserEntity userEntity2 = new UserEntity();

        searchAlertsEntity1.setId(searchAlertEntity1Id);
        searchAlertsEntity1.setUser(userEntity1);
        searchAlertsEntity1.setIsUserNotified(Boolean.FALSE);
        searchAlertsEntity1.setSearchJson(JacksonUtils.toJson(listTripsRequest));
        searchAlertsEntity2.setId(searchAlertEntity2Id);
        searchAlertsEntity2.setUser(userEntity2);
        searchAlertsEntity2.setIsUserNotified(Boolean.TRUE);
        searchAlertsEntity2.setSearchJson(JacksonUtils.toJson(listTripsRequest));

        ListTripsResponse listTripsResponse = new ListTripsResponse();
        listTripsResponse.setCount(count);
        HttpSuccessResponse<ListTripsResponse> successResponse = new HttpSuccessResponse<>(ResponseStatusCodeConstants.SUCCESS, ResponseMessagesConstants.SUCCESS, listTripsResponse);

        when(searchAlertsRepository.findAllByIsUserNotified(Boolean.FALSE)).thenReturn(Arrays.asList(searchAlertsEntity1, searchAlertsEntity2));
        when(listTripsService.listTrips(any(ListTripsRequest.class))).thenReturn(successResponse);
        when(searchAlertsRepository.save(searchAlertsEntity1)).thenReturn(searchAlertsEntity1);
        when(searchAlertsRepository.save(searchAlertsEntity2)).thenReturn(searchAlertsEntity2);

        // Act
        saveSearchAlertService.syncPostsWithSearchAlerts();

        // Assert
        verify(searchAlertsRepository).findAllByIsUserNotified(Boolean.FALSE);

        ArgumentCaptor<SearchAlertsEntity> captor1 = ArgumentCaptor.forClass(SearchAlertsEntity.class);

        verify(searchAlertsRepository, times(wantedNumberofInvocations)).save(captor1.capture());

        ListTripsRequest listTripsRequest1 = JacksonUtils.fromJson(captor1.getValue().getSearchJson(), ListTripsRequest.class);

        verify(listTripsService, times(wantedNumberofInvocations)).listTrips(any(ListTripsRequest.class));
        verify(javaMailSender, times(wantedNumberofInvocations)).send(any(SimpleMailMessage.class));

        assertTrue(captor1.getValue().getIsUserNotified());

    }

}
