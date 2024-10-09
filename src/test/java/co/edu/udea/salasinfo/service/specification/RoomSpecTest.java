package co.edu.udea.salasinfo.service.specification;

import co.edu.udea.salasinfo.dto.request.filter.RoomFilter;
import co.edu.udea.salasinfo.model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;

import static org.assertj.core.api.Assertions.assertThat;

class RoomSpecTest {

    @Mock
    private RoomFilter roomFilter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFilterBy() {
        roomFilter = new RoomFilter("projector", "any", "zoom", null);
        Specification<Room> spec = RoomSpec.filterBy(roomFilter);

        assertThat(spec).isNotNull();
    }

    @Test
    void testFilterBy_Empty() {
        roomFilter = new RoomFilter("", "", "", null);
        Specification<Room> spec = RoomSpec.filterBy(roomFilter);

        assertThat(spec).isNotNull();
    }

    @Test
    void testFilterBy_Nulls() {
        roomFilter = new RoomFilter(null, null, null, null);
        Specification<Room> spec = RoomSpec.filterBy(roomFilter);

        assertThat(spec).isNotNull();
    }
}
