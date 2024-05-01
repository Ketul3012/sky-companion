/**
 * Represents a successful HTTP response.
 * <p>
 * This class is used to encapsulate the response data, status code, and status
 * message returned by an HTTP request. It is important to this project as it
 * provides a standardized way to handle and process successful HTTP responses.
 *
 * @param <T> the type of the response data
 */
package com.sky.companion.common.model;

public class HttpSuccessResponse<T> {

    private Integer statusCode;
    private String statusMessage;
    private T data;

    public HttpSuccessResponse() {
    }

    public HttpSuccessResponse(Integer statusCode, String statusMessage, T data) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.data = data;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public T getData() {
        return data;
    }
}
