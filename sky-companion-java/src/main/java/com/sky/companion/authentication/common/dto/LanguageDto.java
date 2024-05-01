/**
 * This class represents a Data Transfer Object (DTO) for the
 * {@link com.sky.companion.authentication.common.entity.LanguageEntity} class.
 * It is used to transmit user language details over the network.
 * <p>
 * The LanguageDto class contains the following properties:
 * - id: An integer representing the ID of the language.
 * - language: A string representing the language name.
 * <p>
 * This class is an important part of the authentication module in the Sky
 * Companion project.
 * It allows the system to transfer language-related information between
 * different components
 * and communicate with external systems.
 */

package com.sky.companion.authentication.common.dto;

public class LanguageDto {

    private Integer id;
    private String language;

    public LanguageDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
