package com.smartosc.training.repositories;

import com.smartosc.training.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Fresher-Training
 *
 * @author thanhttt
 * @created_at 02/07/2020 - 4:26 PM
 */
@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>, JpaSpecificationExecutor<Hotel> {
    Optional<Hotel> findByName(String name);
}
