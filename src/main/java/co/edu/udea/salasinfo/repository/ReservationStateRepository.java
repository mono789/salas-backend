package co.edu.udea.salasinfo.repository;

import co.edu.udea.salasinfo.model.ReservationState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationStateRepository extends JpaRepository<ReservationState, Long> {
}
