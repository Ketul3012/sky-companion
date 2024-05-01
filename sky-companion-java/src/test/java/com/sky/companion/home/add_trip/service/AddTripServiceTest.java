package com.sky.companion.home.add_trip.service;

import com.sky.companion.authentication.common.dto.UserDTO;
import com.sky.companion.authentication.common.entity.UserEntity;
import com.sky.companion.authentication.common.repository.UserRepository;
import com.sky.companion.common.constants.ResponseStatusCodeConstants;
import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.home.add_trip.request.AddTripRequest;
import com.sky.companion.home.add_trip.request.StopsRequest;
import com.sky.companion.home.common.repository.CompanionProviderTripRepository;
import com.sky.companion.home.common.repository.StopsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class AddTripServiceTest {
    private final float price = 100.0f;

    @InjectMocks
    private AddTripServiceImpl addTripService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CompanionProviderTripRepository companionProviderTripRepository;

    @Mock
    private StopsRepository stopsRepository;

    @Test
    void testAddTripSuccess() {
        // Arrange
        AddTripRequest addTripRequest = new AddTripRequest();
        addTripRequest.setPnr("ABC123");
        addTripRequest.setFrom("CityA");
        addTripRequest.setTo("CityB");
        addTripRequest.setArrivalTime(new Date());
        addTripRequest.setDepartureTime(new Date());
        addTripRequest.setFlightNumber("1234");
        addTripRequest.setPrice(price);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setFirstName("Ketul");
        userEntity.setLastName("Patel");

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(userEntity));

        // Act
        HttpSuccessResponse<String> response = addTripService.addTrip(addTripRequest, userDTO);

        // Assert
        assertEquals(ResponseStatusCodeConstants.SUCCESS, response.getStatusCode());
    }

    @Test
    void testAddTripWithStops() {
        List<StopsRequest> stops = new ArrayList<>();
        StopsRequest stop1 = new StopsRequest();
        stop1.setName("Frankfurt");
        stop1.setArrivalTime(new Date());
        stop1.setDepartureTime(new Date());
        stops.add(stop1);

        // Arrange
        AddTripRequest addTripRequest = new AddTripRequest();
        addTripRequest.setPnr("ABC123");
        addTripRequest.setFrom("CityA");
        addTripRequest.setTo("CityB");
        addTripRequest.setArrivalTime(new Date());
        addTripRequest.setDepartureTime(new Date());
        addTripRequest.setFlightNumber("1234");
        addTripRequest.setPrice(price);
        addTripRequest.setStops(stops);


        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setFirstName("Ketul");
        userEntity.setLastName("Patel");

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(userEntity));

        // Act
        HttpSuccessResponse<String> response = addTripService.addTrip(addTripRequest, userDTO);

        // Assert
        assertEquals(ResponseStatusCodeConstants.SUCCESS, response.getStatusCode());
    }

    @Test
    void testAddTripInvalidPNR() {
        // Arrange
        AddTripRequest addTripRequest = new AddTripRequest();
        addTripRequest.setPnr("456XYZ");
        addTripRequest.setFrom("CityA");
        addTripRequest.setTo("CityB");
        addTripRequest.setArrivalTime(new Date());
        addTripRequest.setDepartureTime(new Date());
        addTripRequest.setFlightNumber("1234");
        addTripRequest.setPrice(price);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setFirstName("John");
        userEntity.setLastName("Doe");

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(userEntity));

        // Act
        HttpSuccessResponse<String> response = addTripService.addTrip(addTripRequest, userDTO);

        // Assert
        assertEquals(ResponseStatusCodeConstants.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testAddTripPNRWithInvalidFirstName() {
        // Arrange
        AddTripRequest addTripRequest = new AddTripRequest();
        addTripRequest.setPnr("ABC123");
        addTripRequest.setFrom("CityA");
        addTripRequest.setTo("CityB");
        addTripRequest.setArrivalTime(new Date());
        addTripRequest.setDepartureTime(new Date());
        addTripRequest.setFlightNumber("1234");
        addTripRequest.setPrice(price);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setFirstName("Ketul");
        userEntity.setLastName("Doe");

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(userEntity));

        // Act
        HttpSuccessResponse<String> response = addTripService.addTrip(addTripRequest, userDTO);

        // Assert
        assertEquals(ResponseStatusCodeConstants.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testAddTripPNRWithInvalidLastName() {
        // Arrange
        AddTripRequest addTripRequest = new AddTripRequest();
        addTripRequest.setPnr("ABC123");
        addTripRequest.setFrom("CityA");
        addTripRequest.setTo("CityB");
        addTripRequest.setArrivalTime(new Date());
        addTripRequest.setDepartureTime(new Date());
        addTripRequest.setFlightNumber("1234");
        addTripRequest.setPrice(price);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setFirstName("John");
        userEntity.setLastName("Patel");

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(userEntity));

        // Act
        HttpSuccessResponse<String> response = addTripService.addTrip(addTripRequest, userDTO);

        // Assert
        assertEquals(ResponseStatusCodeConstants.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testAddTripUserNotPresent() {
        // Arrange
        AddTripRequest addTripRequest = new AddTripRequest();
        addTripRequest.setPnr("ABC123");
        addTripRequest.setFrom("CityA");
        addTripRequest.setTo("CityB");
        addTripRequest.setArrivalTime(new Date());
        addTripRequest.setDepartureTime(new Date());
        addTripRequest.setFlightNumber("1234");
        addTripRequest.setPrice(price);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setFirstName("John");
        userEntity.setLastName("Doe");

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.empty());

        // Act
        HttpSuccessResponse<String> response = addTripService.addTrip(addTripRequest, userDTO);

        // Assert
        assertEquals(ResponseStatusCodeConstants.INVALID_USER, response.getStatusCode());
    }
}
