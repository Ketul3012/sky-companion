/**
 * This class represents the UserLanguageEntity entity, which is equivalent to
 * the user_language table in the database.
 * It is used to handle CRUD operations for the user_language table.
 * This entity is important to the project as it allows the application to
 * manage the language preferences of users.
 * The UserLanguageEntity class extends the AuditModel class, which provides
 * auditing fields such as createdDate and lastModifiedDate.
 */

package com.sky.companion.authentication.common.entity;

import com.sky.companion.common.model.AuditModel;

import javax.persistence.*;

@Entity
@Table(name = "user_language")
public class UserLanguageEntity extends AuditModel {

    /**
     * The composite key consisting of user_id and language.
     */
    @EmbeddedId
    private UserLanguageId userLanguageId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "language_id", insertable = false, updatable = false)
    private LanguageEntity language;

    public UserLanguageEntity() {
    }

    public UserLanguageEntity(UserLanguageId userLanguageId) {
        this.userLanguageId = userLanguageId;
    }

    public UserLanguageId getUserLanguageId() {
        return userLanguageId;
    }
}
