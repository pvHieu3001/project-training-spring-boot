package com.smartosc.training.repositories.specifications;

import com.smartosc.training.entities.Central;
import com.smartosc.training.entities.Central_;
import com.smartosc.training.exceptions.NotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

/**
 * Fresher-Training
 *
 * @author Namtt
 * @created_at 03/07/2020 - 10:09 AM
 * @created_by Namtt
 * @since 03/07/2020
 */
public class CentralSpecification {

  private final List<Specification<Central>> specifications = new ArrayList<>();

  public static CentralSpecification spec() {
    return new CentralSpecification();
  }

  public CentralSpecification hasTitle(String title) {
    if (!StringUtils.isEmpty(title)) {
      specifications.add(
          (root, criteriaQuery, criteriaBuilder) ->
              criteriaBuilder.like(root.get(Central_.TITLE), "%" + title + "%"));
    }
    return this;
  }

  public CentralSpecification hasId(Long id) {
    if (!StringUtils.isEmpty(id)) {
      specifications.add(
          (root, criteriaQuery, criteriaBuilder) ->
              criteriaBuilder.equal(root.get(Central_.ID), id));
    }
    return this;
  }

  public static Specification<Central> all() {
    return ((root, criteriaQuery, criteriaBuilder) ->
        criteriaBuilder.equal(criteriaBuilder.literal(1), 1));
  }

  public Specification<Central> build() {
    return Specification.where(specifications.stream().reduce(all(), Specification::and));
  }
}
