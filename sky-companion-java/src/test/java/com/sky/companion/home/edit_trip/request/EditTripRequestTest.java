package com.sky.companion.home.edit_trip.request;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class EditTripRequestTest {
    private final int expectedId = 123;
    private final Float expectedPrice = 150.0f;

    /**
     * Tests the getter and setter for the 'id' field.
     * Ensures that setting an id value correctly updates the field
     * and can be retrieved using the getter.
     */
    @Test
    void testIdSetterAndGetter() {
        EditTripRequest request = new EditTripRequest();
        request.setId(expectedId);
        assertEquals(expectedId, request.getId(), "The id should match the set value");
    }

    /**
     * Tests the getter and setter for the 'price' field.
     * Ensures that setting a price value correctly updates the field
     * and can be retrieved using the getter.
     */
    @Test
    void testPriceSetterAndGetter() {
        EditTripRequest request = new EditTripRequest();
        request.setPrice(expectedPrice);
        assertEquals(expectedPrice, request.getPrice(), "The price should match the set value");
    }

}
