package com.sky.companion.reviews.save_reviews.controller;

import com.sky.companion.authentication.common.dto.UserDTO;
import com.sky.companion.common.constants.ResponseMessagesConstants;
import com.sky.companion.common.constants.ResponseStatusCodeConstants;
import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.reviews.save_reviews.request.SaveReviewsRequest;
import com.sky.companion.reviews.save_reviews.service.SaveReviewsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test case class for {@link SaveReviewsController} using {@link Mockito}
 */
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
class SaveReviewControllerTest {

    private final int rating = 5;
    @Mock
    private SaveReviewsService saveReviewsService;
    @InjectMocks
    private SaveReviewsController saveReviewsController;

    /**
     * this method test delete-trip endpoint
     */
    @Test
    void testSaveReviewEndpoint() {
        // Arrange
        SaveReviewsRequest saveReviewsRequest = new SaveReviewsRequest();
        saveReviewsRequest.setCompanionId(1);
        saveReviewsRequest.setDescription("Great service!");
        saveReviewsRequest.setRating(rating);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        Mockito.when(saveReviewsService.saveReviews(saveReviewsRequest, userDTO)).thenReturn(new HttpSuccessResponse<>(ResponseStatusCodeConstants.SUCCESS, ResponseMessagesConstants.SUCCESS, ""));

        // Act
        HttpSuccessResponse<String> response = saveReviewsController.saveCompanionReview(saveReviewsRequest, userDTO);

        // Assert
        assertEquals("", response.getData());
    }

}
