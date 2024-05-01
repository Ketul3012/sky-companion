/**
 * The AddressRepository interface is responsible for providing CRUD operations
 * for the AddressEntity class. It extends the JpaRepository interface, which
 * provides generic database operations such as save, delete, and find.
 * <p>
 * This repository is important to the project as it allows the application to
 * interact with the database and perform operations related to the AddressEntity.
 * By using this repository, developers can easily manage and manipulate address
 * data in the database.
 */
package com.sky.companion.authentication.common.repository;

import com.sky.companion.authentication.common.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
}
