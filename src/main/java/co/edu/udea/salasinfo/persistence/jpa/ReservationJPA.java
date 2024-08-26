package co.edu.udea.salasinfo.persistence.jpa;

import co.edu.udea.salasinfo.exceptions.ReservationNotFoundException;
import co.edu.udea.salasinfo.model.Reservation;
import co.edu.udea.salasinfo.model.Room;
import co.edu.udea.salasinfo.persistence.ReservationDAO;
import co.edu.udea.salasinfo.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReservationJPA implements ReservationDAO {
    private final ReservationRepository reservationRepository;

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> findByReservationType(Integer reservationType) {
        return reservationRepository.findByReservationType(reservationType);
    }

    @Override
    public Reservation findFirstByStartsAtAndRoomId(LocalDateTime startsAt, Room roomId) {
        return reservationRepository
                .findFirstByStartsAtAndRoom(startsAt, roomId).orElseThrow(() -> new ReservationNotFoundException(roomId.getRoomId()));
    }

    @Override
    public List<Reservation> findReservationsByRoomIdRoomId(Integer roomId) {
        return reservationRepository.findReservationsByRoomRoomId(roomId);
    }

    @Override
    public boolean existsById(Integer reservationId) {
        return reservationRepository.existsById(reservationId);
    }

    @Override
    public void deleteById(Integer reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    @Override
    public Reservation findById(Integer roomId) {
        return reservationRepository.findById(roomId).orElseThrow(() -> new ReservationNotFoundException(roomId));
    }
}
