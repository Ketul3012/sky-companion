package com.sky.companion.reviews.save_reviews.service;

import com.sky.companion.authentication.common.dto.UserDTO;
import com.sky.companion.authentication.common.entity.UserEntity;
import com.sky.companion.authentication.common.repository.UserRepository;
import com.sky.companion.common.constants.ResponseStatusCodeConstants;
import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.reviews.common.entity.ReviewsEntity;
import com.sky.companion.reviews.common.repository.ReviewsRepository;
import com.sky.companion.reviews.save_reviews.request.SaveReviewsRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SaveReviewsServiceImplTest {
    private final int userId = 2;
    private final int companionId = 77;
    private final int companion2Id = 1;
    private final int rating = 5;
    @InjectMocks
    private SaveReviewsServiceImpl saveReviewsServiceImpl;
    @Mock
    private ReviewsRepository reviewsRepository;
    @Mock
    private UserRepository userRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveReviews() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setId(userId);
        user.setFirstName("Ram");
        user.setLastName("Patel");
        user.setEmail("rp@gmail.com");
        user.setPassword("pass");

        UserEntity companion = new UserEntity();
        user.setId(companionId);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("jd@gmail.com");
        user.setPassword("hashes_pass");

        UserDTO userDTO = new UserDTO();
        userDTO.setId(userId);
        userDTO.setFirstName("Ram");
        SaveReviewsRequest saveReviewsRequest = new SaveReviewsRequest();
        saveReviewsRequest.setCompanionId(companion2Id);
        saveReviewsRequest.setDescription("Great service!");
        saveReviewsRequest.setRating(rating);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDTO.getId());

        UserEntity companionEntity = new UserEntity();
        companionEntity.setId(saveReviewsRequest.getCompanionId());

        ReviewsEntity reviewsEntity = new ReviewsEntity();
        reviewsEntity.setUser(userEntity);
        reviewsEntity.setCompanion(companionEntity);
        reviewsEntity.setDescription(saveReviewsRequest.getDescription());
        reviewsEntity.setRating(saveReviewsRequest.getRating());

        when(userRepository.findById(userDTO.getId())).thenReturn(Optional.of(user));
        when(userRepository.findById(saveReviewsRequest.getCompanionId())).thenReturn(Optional.of(companion));
        when(reviewsRepository.save(any(ReviewsEntity.class))).thenReturn(reviewsEntity);

        // Act
        HttpSuccessResponse<String> response = saveReviewsServiceImpl.saveReviews(saveReviewsRequest, userDTO);

        // Assert
        assertEquals(ResponseStatusCodeConstants.SUCCESS, response.getStatusCode());
    }

    @Test
    void testSaveReviewsUserNotFound() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userId);
        userDTO.setFirstName("Ram");
        SaveReviewsRequest saveReviewsRequest = new SaveReviewsRequest();
        saveReviewsRequest.setCompanionId(1);
        saveReviewsRequest.setDescription("Great service!");
        saveReviewsRequest.setRating(rating);

        when(userRepository.findById(userDTO.getId())).thenReturn(Optional.empty());

        // Act
        HttpSuccessResponse<String> response = saveReviewsServiceImpl.saveReviews(saveReviewsRequest, userDTO);

        // Assert
        assertEquals(ResponseStatusCodeConstants.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void testSaveReviewsCompanionNotFound() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setId(userId);
        user.setFirstName("Ram");
        user.setLastName("Patel");
        user.setEmail("rp@gmail.com");
        user.setPassword("pass");

        UserDTO userDTO = new UserDTO();
        userDTO.setId(userId);
        userDTO.setFirstName("Ram");
        SaveReviewsRequest saveReviewsRequest = new SaveReviewsRequest();
        saveReviewsRequest.setCompanionId(1);
        saveReviewsRequest.setDescription("Great service!");
        saveReviewsRequest.setRating(rating);

        when(userRepository.findById(userDTO.getId())).thenReturn(Optional.of(user));
        when(userRepository.findById(saveReviewsRequest.getCompanionId())).thenReturn(Optional.empty());

        // Act
        HttpSuccessResponse<String> response = saveReviewsServiceImpl.saveReviews(saveReviewsRequest, userDTO);

        // Assert
        assertEquals(ResponseStatusCodeConstants.INVALID_USER, response.getStatusCode());
    }
}
