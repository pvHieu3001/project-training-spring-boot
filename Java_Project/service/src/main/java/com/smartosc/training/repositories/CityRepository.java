package com.smartosc.training.repositories;

import com.smartosc.training.entities.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Fresher-Training
 *
 * @author thanhttt
 * @created_at 03/07/2020 - 2:13 PM
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long>, JpaSpecificationExecutor<City> {
    Page<City> findAll(Pageable p);

    Optional<City> findByName(String key);
}
