package com.sky.companion.common.exceptions;

import com.sky.companion.common.constants.ResponseStatusCodeConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test case class for {@link com.sky.companion.common.exceptions.ExceptionAdvice} using {@link Mockito}
 */
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
class ExceptionAdviceTest {

    @Mock
    private MethodArgumentNotValidException notValidException;

    @Mock
    private Exception exception;

    @InjectMocks
    private ExceptionAdvice exceptionAdvice;

    @Test
    void testHandleMethodArgumentNotValidException() {

        // Arrange
        List<ObjectError> objectErrors = new ArrayList<>();
        ObjectError objectError = new ObjectError("test", "Invalid argument");
        objectErrors.add(objectError);
        Mockito.when(notValidException.getAllErrors()).thenReturn(objectErrors);

        // Act and Assert
        assertEquals(ResponseStatusCodeConstants.BAD_REQUEST, exceptionAdvice.handleException(notValidException).getStatusCode().value());
    }

    @Test
    void testHandleException() {

        // Arrange
        Mockito.when(exception.getLocalizedMessage()).thenReturn("Internal server error");

        // Act and Assert
        assertEquals(ResponseStatusCodeConstants.INTERNAL_SERVER_ERROR, exceptionAdvice.handleException(exception).getStatusCode().value());
    }
}
