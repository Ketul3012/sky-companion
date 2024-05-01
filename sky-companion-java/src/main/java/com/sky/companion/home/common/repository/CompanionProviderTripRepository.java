/**
 * The CompanionProviderTripRepository interface is responsible for providing data access methods
 * for the CompanionProviderTripEntity class. It extends the JpaRepository interface, which provides
 * basic CRUD (Create, Read, Update, Delete) operations for the CompanionProviderTripEntity objects.
 * <p>
 * This file is important to the project as it acts as a bridge between the application and the
 * underlying database, allowing the application to perform database operations related to
 * CompanionProviderTripEntity objects.
 */

package com.sky.companion.home.common.repository;

import com.sky.companion.home.common.entity.CompanionProviderTripEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanionProviderTripRepository extends JpaRepository<CompanionProviderTripEntity, Integer> {
}
