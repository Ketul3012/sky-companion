package com.sky.companion.authentication.get_user.controller;

import com.sky.companion.authentication.common.dto.UserDTO;
import com.sky.companion.authentication.get_user.service.GetUserService;
import com.sky.companion.common.constants.ResponseMessagesConstants;
import com.sky.companion.common.constants.ResponseStatusCodeConstants;
import com.sky.companion.common.exceptions.UserNotFoundException;
import com.sky.companion.common.model.HttpSuccessResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class GetUserControllerTest {

    @Mock
    private GetUserService getUserService;

    @InjectMocks
    private GetUserController getUserController;

    @Test
    void testGetUserByIdSuccess() throws UserNotFoundException {
        // Arrange
        Integer userId = 1;
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userId);
        userDTO.setEmail("test@example.com");
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");

        HttpSuccessResponse<UserDTO> successResponse = new HttpSuccessResponse<>(
                ResponseStatusCodeConstants.SUCCESS,
                ResponseMessagesConstants.SUCCESS,
                userDTO
        );

        Mockito.when(getUserService.getUserById(userId)).thenReturn(successResponse);

        // Act
        HttpSuccessResponse<UserDTO> response = getUserController.getUserById(userId);

        // Assert
        assertEquals(ResponseStatusCodeConstants.SUCCESS, response.getStatusCode());
    }

    @Test
    void testGetUserByIdNotFound() throws UserNotFoundException {
        // Arrange
        Integer userId = 1;

        Mockito.when(getUserService.getUserById(userId)).thenThrow(UserNotFoundException.class);

        // Act and Assert
        assertThrows(UserNotFoundException.class, () -> getUserController.getUserById(userId));
    }
}
