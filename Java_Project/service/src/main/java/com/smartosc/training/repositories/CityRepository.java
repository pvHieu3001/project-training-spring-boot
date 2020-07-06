package com.smartosc.training.repositories;

import com.smartosc.training.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Fresher-Training
 *
 * @author thanhttt
 * @created_at 03/07/2020 - 2:13 PM
 */
public interface CityRepository extends JpaRepository<City, Long>, JpaSpecificationExecutor<City> {
}
