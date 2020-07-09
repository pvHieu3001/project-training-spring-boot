package com.smartosc.training.repositories.specifications;

import com.smartosc.training.entities.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Fresher-Training
 *
 * @author thanhttt
 * @created_at 03/07/2020 - 8:39 AM
 */
@Component
public class HotelSpecification {
    private final List<Specification<Hotel>> personSpecs = new ArrayList<>();

    public static Specification<Hotel> geHotelsByNameSpec(final String searchTerm) {
        return new Specification<Hotel>() {
            @Override
            public Predicate toPredicate(Root<Hotel> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                String likePattern = getLikePattern(searchTerm);
                return criteriaBuilder.like(criteriaBuilder.lower(root.<String>get(Hotel_.name)), likePattern);
            }

            private String getLikePattern(final String searchTerm) {
                StringBuilder pattern = new StringBuilder();
                pattern.append(searchTerm.toLowerCase());
                pattern.append("%");
                return pattern.toString();
            }
        };
    }



    public static Specification<Hotel> getHotelsByCity(City city) {
        return new Specification<Hotel>() {
            @Override
            public Predicate toPredicate(Root<Hotel> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Join<Hotel, City> hotelJoin = root.join(Hotel_.CITY);
                Predicate equalPredicate = criteriaBuilder.equal(hotelJoin.get(City_.name), city);
                return equalPredicate;
            }
        };
    }

    public static Specification<Hotel> getHotelsByTypeRoom(TypeRoom typeRoom) {
        return new Specification<Hotel>() {
            @Override
            public Predicate toPredicate(Root<Hotel> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Join<Hotel, TypeRoom> hotelJoin = root.join(Hotel_.TYPE_ROOMS);
                Predicate equalPredicate = criteriaBuilder.equal(hotelJoin.get(TypeRoom_.name), typeRoom);
                return equalPredicate;
            }
        };
    }


}
