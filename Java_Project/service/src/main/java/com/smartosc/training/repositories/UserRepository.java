package com.smartosc.training.repositories;

import com.smartosc.training.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * Fresher-Training
 *
 * @author Namtt
 * @created_at 02/07/2020 - 10:49 AM
 * @created_by Namtt
 * @since 02/07/2020
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> , JpaSpecificationExecutor<User> {
    User findByUsername(String username);

    @Query(value = "select u.id,u.username,u.email,u.password,u.status from user u where u.status = 1;",nativeQuery = true)
    List<User> findAllByStatus1();


}
