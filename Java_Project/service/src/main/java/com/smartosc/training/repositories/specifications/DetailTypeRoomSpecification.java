package com.smartosc.training.repositories.specifications;

import com.smartosc.training.entities.DetailTypeRoom;
import com.smartosc.training.entities.TypeRoom;
import com.smartosc.training.entities.TypeRoom_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
public class DetailTypeRoomSpecification {
    private final List<Specification<DetailTypeRoom>> personSpecs = new ArrayList<>();
    public static DetailTypeRoomSpecification spec() {
        return new DetailTypeRoomSpecification();
    }

    //find by id
    private DetailTypeRoomSpecification detailTypeRoomHasId(Long id) {
        personSpecs.add((Root<DetailTypeRoom> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) ->
                    criteriaBuilder.equal(root.get(TypeRoom_.ID), id)
        );
        return this;
    }

    //find all
    private Specification<DetailTypeRoom> all() {
        return  (Root<DetailTypeRoom> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) ->
                criteriaBuilder.equal(criteriaBuilder.literal(1), 1);
    }

    public Specification<DetailTypeRoom> build() {
        return Specification.where(personSpecs.stream().reduce(all(), Specification::and));
    }
}
