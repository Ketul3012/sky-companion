/**
 * The JacksonUtils class provides utility methods for converting Java objects
 * to JSON and vice versa using the Jackson library.
 * This file is important to the project as it enables seamless serialization
 * and deserialization of objects to and from JSON format,
 * which is crucial for data exchange and persistence in the application.
 */
package com.sky.companion.common.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JacksonUtils {

    private JacksonUtils() {
    }

    public static String toJson(Object data) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static <T> T fromJson(String value, Class<T> classType) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            return objectMapper.readValue(value, classType);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

}
