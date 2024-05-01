/**
 * The ResponseStatusCodeConstants class defines constants for various HTTP
 * response status codes.
 * These constants are used throughout the project to provide standardized
 * response codes.
 * <p>
 * This file is important to the project because it centralizes the definition
 * of response status codes,
 * making it easier to maintain and ensure consistency across the codebase.
 */
package com.sky.companion.common.constants;

import org.springframework.http.HttpStatus;

public class ResponseStatusCodeConstants {

    public static final int SUCCESS = HttpStatus.OK.value();
    public static final int BAD_REQUEST = HttpStatus.BAD_REQUEST.value();
    public static final int UNAUTHORIZED = HttpStatus.UNAUTHORIZED.value();
    public static final int INTERNAL_SERVER_ERROR = HttpStatus.INTERNAL_SERVER_ERROR.value();
    public static final int INVALID_USER = HttpStatus.NOT_FOUND.value();
    public static final int INVALID_TRIP = HttpStatus.NOT_FOUND.value();

    private ResponseStatusCodeConstants() {
    }

}
