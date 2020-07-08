package com.smartosc.training.repositories;

import com.smartosc.training.entities.Central;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Fresher-Training
 *
 * @author Namtt
 * @created_at 03/07/2020 - 10:07 AM
 * @created_by Namtt
 * @since 03/07/2020
 */
public interface CentralRepository extends JpaRepository<Central,Long>, JpaSpecificationExecutor<Central> {

}
