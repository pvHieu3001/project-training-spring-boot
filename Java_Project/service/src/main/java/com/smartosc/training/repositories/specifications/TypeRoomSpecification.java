package com.smartosc.training.repositories.specifications;

import com.smartosc.training.entities.TypeRoom;
import com.smartosc.training.entities.TypeRoom_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 02/07/2020 - 2:02 PM
 * @created_by Hieupv
 * @since 02/07/2020
 */
@Component
public class TypeRoomSpecification {
    private final List<Specification<TypeRoom>> personSpecs = new ArrayList<>();
    public static TypeRoomSpecification spec() {
        return new TypeRoomSpecification();
    }

    //find by id
    public TypeRoomSpecification typeRoomHasId(Long id) {
        personSpecs.add((Root<TypeRoom> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) ->
                    criteriaBuilder.equal(root.get(TypeRoom_.ID), id)
        );
        return this;
    }

    //find all
    private Specification<TypeRoom> all() {
        return  (Root<TypeRoom> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) ->
                criteriaBuilder.equal(criteriaBuilder.literal(1), 1);

    }

    public Specification<TypeRoom> build() {
        return Specification.where(personSpecs.stream().reduce(all(), Specification::and));
    }
}
