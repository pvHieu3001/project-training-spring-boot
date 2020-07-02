package com.smartosc.training.repositories;

import com.smartosc.training.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Fresher-Training
 *
 * @author Huupd
 * @created_at 02/07/2020 - 1:55 PM
 * @created_by Huupd
 */
public interface RoleRepository extends JpaRepository<Role,Long> {
    List<Role> findByUsersUserName(String username);

    Role findByName(String name);
}