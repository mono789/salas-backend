package co.edu.udea.salasinfo.repository;

import co.edu.udea.salasinfo.model.ReservationState;
import co.edu.udea.salasinfo.utils.enums.RStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationStateRepository extends JpaRepository<ReservationState, Long> {
    Optional<ReservationState> findByState(RStatus status);
}
