/**
 * The ResponseMessagesConstants class contains constant strings that represent
 * various response messages used in the project.
 * These messages are used to provide meaningful feedback to the users or
 * clients of the application.
 * <p>
 * This file is important to the project because it centralizes all the response
 * messages in one place,
 * making it easier to manage and maintain consistency across the application.
 * By using constants, it ensures that the response messages are easily reusable
 * and can be referenced
 * throughout the project without the risk of typos or inconsistencies.
 */
package com.sky.companion.common.constants;

public class ResponseMessagesConstants {

    public static final String SUCCESS = "success";
    public static final String INVALID_USER_CREDENTIALS = "invalid email or password";
    public static final String INTERNAL_SERVER_ERROR = "there's internal server error, please try after some time";
    public static final String VALIDATION_FAILED = "validation failed";
    public static final String INVALID_PNR_DETAILS = "invalid pnr details";
    public static final String INVALID_USER = "user not found";

    private ResponseMessagesConstants() {
    }
}
