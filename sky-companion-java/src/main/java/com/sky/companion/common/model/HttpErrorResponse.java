/**
 * Represents an HTTP error response.
 * <p>
 * This class is used to encapsulate error information in the HTTP responses of
 * the project.
 * It contains the error message and the timestamp when the error occurred.
 * <p>
 * This file is important to the project as it provides a standardized way to
 * handle and communicate errors
 * in the HTTP responses, making it easier for developers to understand and
 * handle errors in the system.
 */
package com.sky.companion.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

public class HttpErrorResponse implements Serializable {

    private final String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private final LocalDateTime timestamp;

    public HttpErrorResponse(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
