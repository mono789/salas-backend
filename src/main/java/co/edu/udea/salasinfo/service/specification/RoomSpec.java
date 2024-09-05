package co.edu.udea.salasinfo.service.specification;

import co.edu.udea.salasinfo.dto.request.filter.RoomFilter;
import co.edu.udea.salasinfo.model.Room;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class RoomSpec {

    private static final String IMPLEMENTS = "implementList";
    private static final String SOFTWARE = "software";
    private static final String NAME = "name";

    private RoomSpec() {
        throw new IllegalStateException("Utility class");
    }

    public static Specification<Room> filterBy(RoomFilter filter) {
        if (filter == null) return null;
        return Specification
                .where(hasImplement(filter.getImplement()))
                .and(hasSoftware(filter.getSoftware()));
    }

    private static Specification<Room> hasImplement(String implement) {
        return (root, query, criteriaBuilder) ->
                implement == null || implement.isEmpty()
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder.like(root.join(IMPLEMENTS)
                        .get(NAME), "%" + implement + "%");
    }

    private static Specification<Room> hasSoftware(String application) {
        return (root, query, criteriaBuilder) ->
                application == null || application.isEmpty()
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder.like(root.join(SOFTWARE)
                        .get(NAME), "%" + application + "%");
    }
}
