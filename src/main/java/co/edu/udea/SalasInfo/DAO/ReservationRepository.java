package co.edu.udea.SalasInfo.DAO;

import co.edu.udea.SalasInfo.Model.Reservation;
import co.edu.udea.SalasInfo.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByReservationType(Integer reservationType);
    Optional<Reservation> findFirstByStartsAt(LocalDateTime startDate);
    List<Reservation> findReservationsByRoomIdRoomId(Integer roomId);
}
