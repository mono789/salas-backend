package co.edu.udea.salasinfo.repository;

import co.edu.udea.salasinfo.model.Reservation;
import co.edu.udea.salasinfo.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByReservationType(Integer reservationType);
    Optional<Reservation> findFirstByStartsAtAndRoomId(LocalDateTime startsAt, Room roomId);
    List<Reservation> findReservationsByRoomIdRoomId(Integer roomId);

}
