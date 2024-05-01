/**
 * The AddTripRequest class represents a request object for adding a trip in the Sky Companion application.
 * It contains various properties such as price, source, destination, departure time, arrival time, flight number, PNR, and stops.
 * This class is important to the project as it serves as a data transfer object (DTO) for capturing the details of a trip to be added.
 * It is used in the API layer to receive and validate the incoming request data before processing it further.
 */

package com.sky.companion.home.add_trip.request;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

public class AddTripRequest {

    private Float price;

    @NotEmpty(message = "Source cannot be empty.")
    @Size(min = 2, message = "Source must be at least 2 characters long")
    private String from;

    @NotEmpty(message = "Destination cannot be empty.")
    @Size(min = 2, message = "Destination must be at least 2 characters long")
    private String to;

    @NotNull(message = "Departure Time cannot be empty.")
    private Date departureTime;

    @NotNull(message = "Arrival Time cannot be empty.")
    private Date arrivalTime;

    @NotEmpty(message = "Flight number cannot be empty.")
    @Size(min = 2, message = "Flight Number must be at least 2 characters long")
    private String flightNumber;

    @NotEmpty(message = "PNR cannot be empty.")
    @Size(min = 6, message = "PNR must be at least 6 characters long")
    private String pnr;

    private List<StopsRequest> stops;

    public AddTripRequest() {
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public List<StopsRequest> getStops() {
        return stops;
    }

    public void setStops(List<StopsRequest> stops) {
        this.stops = stops;
    }
}
