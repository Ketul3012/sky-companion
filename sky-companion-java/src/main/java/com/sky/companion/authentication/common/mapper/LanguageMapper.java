/**
 * Mapper class used to run mapping from and to between
 * {@link com.sky.companion.authentication.common.entity.LanguageEntity} and
 * {@link com.sky.companion.authentication.common.dto.LanguageDto}.
 * This class provides the functionality to convert LanguageEntity objects to
 * LanguageDto objects and vice versa.
 * It is an important component of the authentication module in the Sky
 * Companion project.
 * The LanguageMapper class is responsible for mapping language-related data
 * between the data access layer (LanguageEntity) and the presentation layer
 * (LanguageDto).
 * This mapping is necessary to ensure that the data is correctly transferred
 * and represented in different parts of the application.
 */

package com.sky.companion.authentication.common.mapper;

import com.sky.companion.authentication.common.dto.LanguageDto;
import com.sky.companion.authentication.common.entity.LanguageEntity;

public class LanguageMapper {

    private LanguageMapper() {
    }

    /**
     * Maps {@link com.sky.companion.authentication.common.entity.LanguageEntity} to
     * {@link com.sky.companion.authentication.common.dto.LanguageDto}.
     *
     * @param entity the LanguageEntity object to be mapped
     * @return the corresponding LanguageDto object
     */
    public static LanguageDto entityToDTO(LanguageEntity entity) {
        LanguageDto dto = new LanguageDto();
        dto.setId(entity.getId());
        dto.setLanguage(entity.getLanguage());
        return dto;
    }

}
