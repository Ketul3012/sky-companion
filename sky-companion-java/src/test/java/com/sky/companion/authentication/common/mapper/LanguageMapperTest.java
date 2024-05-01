package com.sky.companion.authentication.common.mapper;

import com.sky.companion.authentication.common.dto.LanguageDto;
import com.sky.companion.authentication.common.entity.LanguageEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LanguageMapperTest {

    @Test
    void testEntityToDTOMapping() {
        // Arrange
        LanguageEntity languageEntity = new LanguageEntity();
        languageEntity.setId(1);
        languageEntity.setLanguage("English");

        LanguageDto languageDto = new LanguageDto();
        languageDto.setId(1);
        languageDto.setLanguage("English");

        // Act and Assert
        assertThat(languageDto).usingRecursiveComparison().isEqualTo(LanguageMapper.entityToDTO(languageEntity));
    }
}
