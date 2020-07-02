package com.smartosc.training.repositories;

import com.smartosc.training.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Fresher-Training
 *
 * @author Namtt
 * @created_at 02/07/2020 - 10:49 AM
 * @created_by Namtt
 * @since 02/07/2020
 */
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserName(String username);
}
