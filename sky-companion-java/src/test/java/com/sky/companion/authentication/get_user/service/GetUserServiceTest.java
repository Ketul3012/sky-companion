package com.sky.companion.authentication.get_user.service;

import com.sky.companion.authentication.common.dto.UserDTO;
import com.sky.companion.authentication.common.entity.UserEntity;
import com.sky.companion.authentication.common.repository.UserRepository;
import com.sky.companion.common.exceptions.UserNotFoundException;
import com.sky.companion.common.model.HttpSuccessResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class GetUserServiceTest {

    @InjectMocks
    private GetUserServiceImpl getUserService;

    @Mock
    private UserRepository userRepository;

    @Test
    void testGetUserByIdSuccess() throws UserNotFoundException {
        // Arrange
        int userId = 1;
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        userEntity.setEmail("test@example.com");
        userEntity.setFirstName("John");
        userEntity.setLastName("Doe");

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        HttpSuccessResponse<UserDTO> response = getUserService.getUserById(userId);

        // Assert
        assertNotNull(response);
    }

    @Test
    void testGetUserByIdNotFound() {
        // Arrange
        int userId = 1;
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(UserNotFoundException.class, () -> getUserService.getUserById(userId));
    }
}
