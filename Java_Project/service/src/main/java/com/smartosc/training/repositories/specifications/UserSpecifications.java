package com.smartosc.training.repositories.specifications;

import com.smartosc.training.entities.User;
import com.smartosc.training.entities.User_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
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

    //find all user
    public Specification<User> getAllUser() {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.literal(1), 1));
    }

    public Specification<User> buildGetAll() {
        return Specification.where(specializations.stream().reduce(getAllUser(), Specification::and));
    }

    //find by Id
    public Specification<User> getUserById(Long id) {
        return StringUtils.isEmpty(id) ? getAllUser() : (root, query, criteriaBuilder) ->
        {
            query.distinct(true);
            return criteriaBuilder.equal(root.get(User_.ID), id);
        };
    }

    public void buildGetById(Long id) {
        specializations.add(getUserById(id));
    }


}
