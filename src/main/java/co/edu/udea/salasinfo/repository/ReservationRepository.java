package co.edu.udea.salasinfo.repository;

import co.edu.udea.salasinfo.model.Reservation;
import co.edu.udea.salasinfo.model.Room;
import co.edu.udea.salasinfo.utils.enums.ReservationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByType(ReservationType reservationType);
    Optional<Reservation> findFirstByStartsAtAndRoom(LocalDateTime startsAt, Room roomId);
    List<Reservation> findReservationsByRoomRoomId(Long roomId);

}
