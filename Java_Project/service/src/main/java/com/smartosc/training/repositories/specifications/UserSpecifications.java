package com.smartosc.training.repositories.specifications;

import com.smartosc.training.entities.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Root;
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

    public Specification<User> build() {
        return Specification.where(specializations.stream().reduce(getAllUser(), Specification::and));
    }

}
