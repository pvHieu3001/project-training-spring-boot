package com.smartosc.training.repositories.specifications;

import com.smartosc.training.entities.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Fresher-Training
 *
 * @author Huupd
 * @created_at 06/07/2020 - 4:36 PM
 * @created_by Huupd
 */
@Component
public class UserSpecifications {
    private final List<Specification<User>> specializations = new ArrayList<>();


    public static UserSpecifications spec() {
        return new UserSpecifications();
    }

    //find all
    public Specification<User> all() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.literal(1), 1);
    }

    public Specification<User> build() {
        return Specification.where(specializations.stream().reduce(all(), Specification::and));
    }

    //find by Id
    public Specification<User> hasId(Long id) {
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);
            if (id != null) {
             //  return criteriaBuilder.equal(root.get(User_.ID), id);
                return criteriaBuilder.equal(root.get("id"), criteriaBuilder.literal(id));
            } else {
                return null;
            }
        };
    }
//        return StringUtils.isEmpty(id) ? all() : (root, query, criteriaBuilder) -> {
//            query.distinct(true);
//            return criteriaBuilder.equal(root.get(User_.ID), id);
//        };

//}
//
//    public void byUserId(Long id) {
//        specializations.add(hasId(id));
//    }
}
