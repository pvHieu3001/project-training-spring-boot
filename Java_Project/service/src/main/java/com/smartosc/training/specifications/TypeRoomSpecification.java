package com.smartosc.training.specifications;

import com.smartosc.training.entities.TypeRoom;
import com.smartosc.training.entities.TypeRoom_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
public class TypeRoomSpecification {
    private final List<Specification<TypeRoom>> personSpecs = new ArrayList<>();

    //find by id
    public void byId(Long id) {
        personSpecs.add(hasId(id));
    }

    private Specification<TypeRoom> hasId(Long id) {
        return StringUtils.isEmpty(id) ? all() : (Root<TypeRoom> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            criteriaQuery.distinct(true);
            return criteriaBuilder.equal(root.get(TypeRoom_.ID), id);
        };
    }

    //find all
    private Specification<TypeRoom> all() {
        return (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.equal(criteriaBuilder.literal(1), 1);
    }

    public Specification<TypeRoom> build() {
        return Specification.where(personSpecs.stream().reduce(all(), Specification::and));
    }
}
