/**
 * The AuditModel class is an abstract class that serves as the base model for
 * entities that require auditing.
 * It provides fields and annotations for tracking the creation and last update
 * timestamps of the entities.
 * This class is important to the project as it ensures that all audited
 * entities have the necessary fields and annotations
 * for tracking their creation and last update timestamps.
 */

package com.sky.companion.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdStamp", "lastUpdatedStamp"}, allowGetters = true)
public abstract class AuditModel implements Serializable {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdStamp;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private Date lastUpdatedStamp;

    public AuditModel() {
    }

    public Date getCreatedStamp() {
        return createdStamp;
    }

    public Date getLastUpdatedStamp() {
        return lastUpdatedStamp;
    }
}
