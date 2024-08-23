package co.edu.udea.SalasInfo.persistence;

import co.edu.udea.SalasInfo.model.Reservation;
import co.edu.udea.SalasInfo.model.Room;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationDAO{
    Reservation save(Reservation reservation);
    List<Reservation> findAll();
    List<Reservation> findByReservationType(Integer reservationType);
    Reservation findFirstByStartsAtAndRoomId(LocalDateTime startsAt, Room roomId);
    List<Reservation> findReservationsByRoomIdRoomId(Integer roomId);
    boolean existsById(Integer reservationId);
    void deleteById(Integer reservationId);
    Reservation findById(Integer roomId);
}
