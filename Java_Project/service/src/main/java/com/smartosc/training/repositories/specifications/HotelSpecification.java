package com.smartosc.training.repositories.specifications;

import com.smartosc.training.entities.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;

/**
 * Fresher-Training
 *
 * @author thanhttt
 * @created_at 03/07/2020 - 8:39 AM
 */
@Component
public class HotelSpecification {

    public static Specification<Hotel> geHotelsByNameSpec(final String searchTerm) {
        return new Specification<Hotel>() {
            @Override
            public Predicate toPredicate(Root<Hotel> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                String likePattern = getLikePattern(searchTerm);
                return criteriaBuilder.like(criteriaBuilder.lower(root.<String>get(Hotel_.name)), likePattern);
            }

            private String getLikePattern(final String searchTerm) {
                StringBuilder pattern = new StringBuilder();
                pattern.append("%");
                pattern.append(searchTerm.toLowerCase());
                pattern.append("%");
                return pattern.toString();
            }
        };
    }

    public static Specification<Hotel> getHotelsByCity(String city) {
        return (root, query, criteriaBuilder) -> {
            Join<Hotel, City> hotelJoin = root.join(Hotel_.city);
            Predicate equalPredicate = criteriaBuilder.like(hotelJoin.get(City_.name),"%"+city+"%");
            query.distinct(true);
            return equalPredicate;
        };
    }

    public static Specification<Hotel> getHotelsByTypeRoom(String typeRoom) {
        return (root, query, criteriaBuilder) -> {
                Join<Hotel, TypeRoom> hotelJoin = root.join(Hotel_.typeRooms);
                Predicate equalPredicate = criteriaBuilder.like(hotelJoin.get(TypeRoom_.name),typeRoom);
                query.distinct(true);
                return equalPredicate;
        };
    }


}
