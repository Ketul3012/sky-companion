/**
 * The StopsRepository interface is responsible for providing data access methods
 * for the StopsEntity class. It extends the JpaRepository interface, which provides
 * generic CRUD (Create, Read, Update, Delete) operations for the StopsEntity class.
 * <p>
 * This file is important to the project as it acts as a bridge between the application
 * and the underlying database. It allows the application to perform database operations
 * on the StopsEntity objects, such as saving, retrieving, updating, and deleting stops data.
 */

package com.sky.companion.home.common.repository;

import com.sky.companion.home.common.entity.StopsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StopsRepository extends JpaRepository<StopsEntity, Integer> {
}
