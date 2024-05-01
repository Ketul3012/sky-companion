/**
 * The UserLanguageId class represents a composite key for the user_language
 * table.
 * It is used to uniquely identify a user's language preference in the project.
 * <p>
 * This class is marked as an Embeddable, which means it can be embedded within
 * other entities.
 * It contains two fields: userId and languageId, which are references to the
 * user_id and language_id columns in the database.
 * <p>
 * This class implements the Serializable interface to support serialization and
 * deserialization of objects.
 * <p>
 * This file is important to the project as it defines the composite key for the
 * user_language table,
 * allowing the application to establish relationships between users and their
 * preferred languages.
 */

package com.sky.companion.authentication.common.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserLanguageId implements Serializable {

    /**
     * reference to user_id from user table
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * reference to language_id from language table
     */
    @Column(name = "language_id")
    private Integer languageId;

    public UserLanguageId() {
    }

    public UserLanguageId(Integer userId, Integer languageId) {
        this.userId = userId;
        this.languageId = languageId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }
}
