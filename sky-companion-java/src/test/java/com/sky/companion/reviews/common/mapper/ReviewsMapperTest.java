package com.sky.companion.reviews.common.mapper;

import com.sky.companion.authentication.common.entity.UserEntity;
import com.sky.companion.reviews.common.dto.ReviewsDTO;
import com.sky.companion.reviews.common.entity.ReviewsEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
class ReviewsMapperTest {
    private static ReviewsEntity getReviewsEntity() {
        UserEntity user = new UserEntity();
        user.setId(1);
        user.setFirstName("Ram");
        user.setLastName("Patel");
        user.setEmail("rp@gmail.com");
        user.setPassword("pass");

        UserEntity companion = new UserEntity();
        user.setId(1);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("jd@gmail.com");
        user.setPassword("hashes_pass");

        ReviewsEntity reviewsEntity = new ReviewsEntity();
        reviewsEntity.setId(1);
        reviewsEntity.setUser(user);
        reviewsEntity.setCompanion(companion);
        reviewsEntity.setRating(1);
        reviewsEntity.setDescription("Good Service.");
        return reviewsEntity;
    }

    @Test
    void testEntityToDTOMapping() {
        // Arrange
        ReviewsEntity reviewsEntity = getReviewsEntity();

        // Act
        ReviewsDTO reviewsDTO = ReviewsMapper.entityToDTO(reviewsEntity);

        // Assert
        assertThat(reviewsDTO.getId()).usingRecursiveComparison().ignoringFields("userFirstName", "userLastName", "companionFirstName", "companionLastName").isEqualTo(reviewsEntity.getId());
    }

    @Test
    void testCalculateRatings() {
        // Arrange
        ReviewsEntity reviewsEntity1 = getReviewsEntity();
        ReviewsEntity reviewsEntity2 = getReviewsEntity();

        Set<ReviewsEntity> reviewsEntities = new HashSet<>();
        reviewsEntities.add(reviewsEntity1);
        reviewsEntities.add(reviewsEntity2);
        // Act
        Double calculateRatings = ReviewsMapper.calculateRatings(reviewsEntities);

        // Assert
        Assertions.assertEquals(1, calculateRatings);
    }
}
