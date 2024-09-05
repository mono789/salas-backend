package co.edu.udea.salasinfo.persistence.jpa;

import co.edu.udea.salasinfo.exceptions.ReservationNotFoundException;
import co.edu.udea.salasinfo.model.Reservation;
import co.edu.udea.salasinfo.model.Room;
import co.edu.udea.salasinfo.persistence.ReservationDAO;
import co.edu.udea.salasinfo.repository.ReservationRepository;
import co.edu.udea.salasinfo.utils.enums.ReservationType;
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
    public List<Reservation> findByType(ReservationType reservationType) {
        return reservationRepository.findByType(reservationType);
    }

    @Override
    public Reservation findFirstByStartsAtAndRoomId(LocalDateTime startsAt, Room roomId) {
        return reservationRepository
                .findFirstByStartsAtAndRoom(startsAt, roomId).orElseThrow(() -> new ReservationNotFoundException(roomId.getId()));
    }

    @Override
    public List<Reservation> findReservationsByRoomIdRoomId(Long roomId) {
        return reservationRepository.findReservationsByRoomId(roomId);
    }

    @Override
    public boolean existsById(Long reservationId) {
        return reservationRepository.existsById(reservationId);
    }

    @Override
    public void deleteById(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    @Override
    public Reservation findById(Long roomId) {
        return reservationRepository.findById(roomId).orElseThrow(() -> new ReservationNotFoundException(roomId));
    }
}
