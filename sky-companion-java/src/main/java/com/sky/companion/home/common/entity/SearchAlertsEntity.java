/**
 * The SearchAlertsEntity class represents a search alert entity in the system.
 * It is used to store information about a search alert, including the user who created it,
 * the search criteria in JSON format, and whether the user has been notified about the alert.
 * <p>
 * This entity is important to the project as it allows users to create and manage search alerts,
 * which are used to notify them when certain criteria are met. It provides a way to store and retrieve
 * search alerts from the database, as well as track whether a user has been notified about an alert.
 * <p>
 * This entity extends the AuditModel class, which provides auditing fields such as created and updated timestamps.
 */
package com.sky.companion.home.common.entity;

import com.sky.companion.authentication.common.entity.UserEntity;
import com.sky.companion.common.model.AuditModel;

import javax.persistence.*;

@Entity
@Table(name = "search_alerts")
public class SearchAlertsEntity extends AuditModel {

    /**
     * auto generated id, used as a primary key
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * reference to user table, multiple search alerts can be created by user
     */
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    /**
     * column to store search alert json
     */
    @Column(name = "search_json", nullable = false, length = 2048)
    private String searchJson;

    /**
     * column to identify that user is notified for search alert or not
     */
    @Column(name = "is_user_notified", columnDefinition = "boolean default false")
    private Boolean isUserNotified;

    public SearchAlertsEntity() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return this.user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getSearchJson() {
        return this.searchJson;
    }

    public void setSearchJson(String searchJson) {
        this.searchJson = searchJson;
    }

    public Boolean getIsUserNotified() {
        return this.isUserNotified;
    }

    public void setIsUserNotified(Boolean isUserNotified) {
        this.isUserNotified = isUserNotified;
    }
}
