package com.sky.companion.authentication.common.mapper;

import com.sky.companion.authentication.common.dto.AddressDto;
import com.sky.companion.authentication.common.dto.LanguageDto;
import com.sky.companion.authentication.common.dto.UserDTO;
import com.sky.companion.authentication.common.entity.AddressEntity;
import com.sky.companion.authentication.common.entity.LanguageEntity;
import com.sky.companion.authentication.common.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test case class for {@link com.sky.companion.home.common.mapper.CompanionProviderTripMapper} using {@link org.mockito.Mockito}
 */
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
class UserMapperTest {

    private static final Date DOB = new Date(System.currentTimeMillis());

    private static UserDTO getUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setFirstName("test");
        userDTO.setLastName("test");
        userDTO.setDob(DOB);
        userDTO.setEmail("test");
        userDTO.setMobileNumber("test");
        userDTO.setActive(Boolean.TRUE);
        return userDTO;
    }

    private static void addLanguagesInUserDTO(UserDTO userDTO) {
        LanguageDto languageDto = new LanguageDto();
        languageDto.setId(1);
        languageDto.setLanguage("English");

        List<LanguageDto> languageDtos = new ArrayList<>();
        languageDtos.add(languageDto);
        userDTO.setLanguages(languageDtos);
    }

    private static void addLanguagesInUserEntity(UserEntity userEntity) {
        LanguageEntity languageEntity = new LanguageEntity();
        languageEntity.setId(1);
        languageEntity.setLanguage("English");

        Set<LanguageEntity> languageEntities = new HashSet<>();
        languageEntities.add(languageEntity);
        userEntity.setLanguages(languageEntities);
    }

    private static UserEntity getUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setFirstName("test");
        userEntity.setLastName("test");
        userEntity.setDob(DOB);
        userEntity.setEmail("test");
        userEntity.setMobileNumber("test");
        userEntity.setActive(Boolean.TRUE);
        return userEntity;
    }

    private static void addAddressInUserDTO(UserDTO userDTO) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(1);
        addressDto.setAddressLine1("123 Main St");
        addressDto.setAddressLine2("Apt 456");
        addressDto.setCity("City");
        addressDto.setProvince("Province");
        addressDto.setCountry("Country");
        addressDto.setPostalCode("12345");
        userDTO.setAddress(addressDto);
    }

    private static void addAddressInUserEntity(UserEntity userEntity) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(1);
        addressEntity.setAddressLine1("123 Main St");
        addressEntity.setAddressLine2("Apt 456");
        addressEntity.setCity("City");
        addressEntity.setProvince("Province");
        addressEntity.setCountry("Country");
        addressEntity.setPostalCode("12345");
        userEntity.setAddress(addressEntity);
    }

    /**
     * This method run the unit test case for {@link UserMapper} entity to dto conversion
     */
    @Test
    void testUserMapperEntityToDto() {

        // Arrange
        UserEntity userEntity = getUserEntity();

        UserDTO userDTO = getUserDTO();

        // Act and Assert
        assertThat(userDTO).usingRecursiveComparison().isEqualTo(UserMapper.entityToDTO(userEntity));
    }

    /**
     * This method run the unit test case for {@link UserMapper} entity to dto conversion
     */
    @Test
    void testUserMapperEntityToDtoWithSubEntities() {

        // Arrange
        UserEntity userEntity = getUserEntity();

        addLanguagesInUserEntity(userEntity);

        addAddressInUserEntity(userEntity);

        UserDTO userDTO = getUserDTO();

        addLanguagesInUserDTO(userDTO);

        addAddressInUserDTO(userDTO);

        // Act and Assert
        assertThat(userDTO).usingRecursiveComparison().isEqualTo(UserMapper.entityToDTO(userEntity));
    }
}
