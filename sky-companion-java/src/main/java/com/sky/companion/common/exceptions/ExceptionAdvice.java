/**
 * This class serves as an advice component for handling exceptions in the
 * application.
 * It provides centralized exception handling and response generation for
 * specific types of exceptions.
 * This file is important to the project as it helps in maintaining a consistent
 * and standardized approach
 * for handling exceptions throughout the application.
 */
package com.sky.companion.common.exceptions;

import com.sky.companion.common.constants.ResponseStatusCodeConstants;
import com.sky.companion.common.model.HttpErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    /**
     * This method used to handle {@link MethodArgumentNotValidException} exception
     * and give centralized response
     *
     * @param e exception which got caught
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<HttpErrorResponse> handleException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(new HttpErrorResponse(e.getAllErrors().get(0).getDefaultMessage()),
                HttpStatus.valueOf(ResponseStatusCodeConstants.BAD_REQUEST));
    }

    /**
     * This method used to handle generic {@link Exception} and give centralized
     * response
     *
     * @param e exception which got caught
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<HttpErrorResponse> handleException(Exception e) {
        return new ResponseEntity<>(new HttpErrorResponse(e.getLocalizedMessage()),
                HttpStatus.valueOf(ResponseStatusCodeConstants.INTERNAL_SERVER_ERROR));
    }

}
