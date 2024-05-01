/**
 * This class represents an exception that is thrown when a user is not found in
 * the system.
 * It extends the Exception class.
 * <p>
 * This file is important to the project because it provides a specific
 * exception type for handling cases
 * where a user is not found. By throwing this exception, the application can
 * handle the error gracefully
 * and provide appropriate feedback to the user.
 */

package com.sky.companion.common.exceptions;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super("User not found in system");
    }

}
