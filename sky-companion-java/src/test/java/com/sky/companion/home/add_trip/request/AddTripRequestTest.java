package com.sky.companion.home.add_trip.request;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTripRequestTest {
    private final float price = 100.0f;

    @Test
    void testInvalidFlightNumber() {
        String flightNumber = "1234";
        AddTripRequest addTripRequest = new AddTripRequest();
        addTripRequest.setPnr("ABC123");
        addTripRequest.setFrom("CityA");
        addTripRequest.setTo("CityB");
        addTripRequest.setArrivalTime(new Date());
        addTripRequest.setDepartureTime(new Date());
        addTripRequest.setFlightNumber("1234");
        addTripRequest.setPrice(price);

        assertEquals(flightNumber, addTripRequest.getFlightNumber());
    }
}
