package com.smartosc.training.specifications;

import com.smartosc.training.entities.Central;
import com.smartosc.training.entities.Central_;
import java.util.ArrayList;
import java.util.List;
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
public final class CentralSpecification {

  private final List<Specification<Central>> specifications = new ArrayList<>();

  public static CentralSpecification spec() {
    return new CentralSpecification();
  }

  public void byTitle(String title) {
    specifications.add(hasTitle(title));
  }

  private Specification<Central> hasTitle(String title) {
    return StringUtils.isEmpty(title) ? all() : (root, criteriaQuery, criteriaBuilder) -> {
      criteriaQuery.distinct(true);
      return criteriaBuilder.like(root.get(Central_.TITLE), "%" + title + "%");
    };
  }

  public static Specification<Central> all() {
    return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
        .equal(criteriaBuilder.literal(1), 1));
  }

  public Specification<Central> build() {
    return Specification.where(specifications.stream().reduce(all(), Specification::and));
  }
}
