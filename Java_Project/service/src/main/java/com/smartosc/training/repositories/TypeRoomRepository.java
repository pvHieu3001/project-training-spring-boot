package com.smartosc.training.repositories;

import com.smartosc.training.entities.TypeRoom;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 02/07/2020 - 1:56 PM
 * @created_by Hieupv
 * @since 02/07/2020
 */
@Repository
public interface TypeRoomRepository extends JpaRepository<TypeRoom, Long>, JpaSpecificationExecutor<TypeRoom> {
    TypeRoom findByName(String name);
}
