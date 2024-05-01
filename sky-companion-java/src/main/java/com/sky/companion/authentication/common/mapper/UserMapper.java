/**
 * This class is a mapper used to convert objects between {@link UserEntity} and
 * {@link UserDTO}.
 * It provides methods to map a {@link UserEntity} object to a {@link UserDTO}
 * object and vice versa.
 * <p>
 * This mapper is important to the project as it facilitates the conversion of
 * user data between the database entity representation
 * ({@link UserEntity}) and the data transfer object representation
 * ({@link UserDTO}). It allows for seamless communication
 * between the persistence layer and the application layer, ensuring that the
 * correct data is passed between them.
 * The mapper also handles additional transformations such as calculating
 * ratings and sorting reviews.
 */

package com.sky.companion.authentication.common.mapper;

import com.sky.companion.authentication.common.dto.UserDTO;
import com.sky.companion.authentication.common.entity.UserEntity;
import com.sky.companion.common.model.AuditModel;
import com.sky.companion.reviews.common.mapper.ReviewsMapper;

import java.util.Comparator;
import java.util.stream.Collectors;

import static com.sky.companion.reviews.common.mapper.ReviewsMapper.calculateRatings;

public class UserMapper {

    private UserMapper() {
    }

    /**
     * maps {@link UserEntity} to {@link UserDTO}
     *
     * @param entity entity to mapped
     * @return dto generated from entity
     */
    public static UserDTO entityToDTO(UserEntity entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setActive(entity.getActive());
        dto.setMobileNumber(entity.getMobileNumber());
        dto.setDob(entity.getDob());
        dto.setLanguages(entity.getLanguages() != null
                ? entity.getLanguages().stream().map(LanguageMapper::entityToDTO).collect(Collectors.toList())
                : null);
        dto.setAddress(entity.getAddress() != null ? AddressMapper.entityToDTO(entity.getAddress()) : null);
        dto.setRatings(entity.getReviews() != null ? calculateRatings(entity.getReviews()) : null);
        dto.setReviews(entity.getReviews() != null
                ? entity.getReviews().stream().sorted(Comparator.comparing(AuditModel::getCreatedStamp).reversed())
                .map(ReviewsMapper::entityToDTO).collect(Collectors.toList())
                : null);
        if (entity.getLanguages() != null) {
            dto.setLanguages(
                    entity.getLanguages().stream().map(LanguageMapper::entityToDTO).collect(Collectors.toList()));
        }
        if (entity.getAddress() != null) {
            dto.setAddress(AddressMapper.entityToDTO(entity.getAddress()));
        }
        return dto;
    }

}
