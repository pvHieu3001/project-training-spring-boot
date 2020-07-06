package com.smartosc.training.repositories;

import com.smartosc.training.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;

/**
 * Fresher-Training
 *
 * @author Namtt
 * @created_at 02/07/2020 - 10:49 AM
 * @created_by Namtt
 * @since 02/07/2020
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("FROM User u  WHERE  u.username LIKE %:username%")
    Optional<User> findByUsername11(@Param("username") String username);

    User findByUsername(String name);
}
