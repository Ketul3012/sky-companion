/**
 * This class represents the LanguageEntity entity, which is equivalent to the
 * language table in the database.
 * It is used to handle CRUD operations for the language table.
 * <p>
 * The LanguageEntity class extends the AuditModel class, which provides
 * auditing fields such as createdDate, createdBy, etc.
 * <p>
 * The LanguageEntity class has the following attributes:
 * - id: Integer, used as the primary key for the language table.
 * - language: String, represents the language column where user-added languages
 * can be stored.
 * <p>
 * This file is important to the project as it defines the entity class for the
 * language table, allowing CRUD operations to be performed on it.
 */

package com.sky.companion.authentication.common.entity;

import com.sky.companion.common.model.AuditModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "language")
public class LanguageEntity extends AuditModel {

    /**
     * used as a primary key
     */
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * language column where user added language can be stored
     */
    @Column(name = "language", length = 20)
    private String language;

    public LanguageEntity() {
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
