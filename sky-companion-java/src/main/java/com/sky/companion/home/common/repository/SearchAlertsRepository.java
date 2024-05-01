/**
 * The SearchAlertsRepository interface is responsible for defining the methods to interact with the database
 * for managing search alerts.
 * <p>
 * This repository is important to the project as it provides the necessary functionality to retrieve and manage
 * search alerts stored in the database. It extends the JpaRepository interface, which provides basic CRUD operations
 * for the SearchAlertsEntity class.
 */

package com.sky.companion.home.common.repository;

import com.sky.companion.home.common.entity.SearchAlertsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchAlertsRepository extends JpaRepository<SearchAlertsEntity, Integer> {

    /**
     * Retrieves a list of search alerts based on the specified user notification
     * status.
     *
     * @param isUserNotified The user notification status to filter the search
     *                       alerts.
     * @return A list of search alerts that match the specified user notification
     * status.
     */
    List<SearchAlertsEntity> findAllByIsUserNotified(Boolean isUserNotified);

}
