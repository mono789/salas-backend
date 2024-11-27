package co.edu.udea.salasinfo.repository;

import co.edu.udea.salasinfo.model.Room;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAll(@Nullable Specification<Room> specification);
}
