package com.smartosc.training.repositories.specifications;

import com.smartosc.training.entities.User;
import com.smartosc.training.entities.User_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Fresher-Training
 *
 * @author Tung lam
 * @created_at 06/07/2020 - 15:23
 * @created_by Tung lam
 * @since 06/07/2020
 */
public final class UserSpecification {
    private final List<Specification<User>> specifications = new ArrayList<>();

    public static UserSpecification specification() {
        return new UserSpecification();
    }

    public static Specification<User> getAllUser() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(criteriaBuilder.literal(1), 1);
    }

    private Specification<User> hasName(String name) {
        return StringUtils.isEmpty(name) ? getAllUser() : (Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            criteriaQuery.distinct(true);
            return criteriaBuilder.like(root.get(User_.USERNAME), "%" + name + "%");
        };
    }

    public Specification<User> buildGetAll() {
        return Specification.where(specifications.stream().reduce(getAllUser(), Specification::and));
    }


}
