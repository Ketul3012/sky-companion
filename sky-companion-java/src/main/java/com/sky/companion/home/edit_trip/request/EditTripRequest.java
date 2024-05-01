/**
 * The EditTripRequest class represents a request to edit a trip.
 * It contains the necessary data for modifying a trip, such as the trip ID and the new price.
 * This class is important to the project as it serves as a data transfer object (DTO) for updating trip information.
 * It allows the application to receive and process requests to modify trip details from the user interface or API endpoints.
 */

package com.sky.companion.home.edit_trip.request;

import javax.validation.constraints.NotNull;

public class EditTripRequest {

    @NotNull(message = "Id cannot be null")
    private Integer id;

    private Float price;

    public EditTripRequest() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
