package co.edu.udea.SalasInfo.persistence.jpa;

import co.edu.udea.SalasInfo.exceptions.ReservationNotFoundException;
import co.edu.udea.SalasInfo.model.Reservation;
import co.edu.udea.SalasInfo.model.Room;
import co.edu.udea.SalasInfo.persistence.ReservationDAO;
import co.edu.udea.SalasInfo.repository.ReservationRepository;
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
                .findFirstByStartsAtAndRoomId(startsAt, roomId).orElseThrow(() -> new ReservationNotFoundException(roomId.getRoomId()));
    }

    @Override
    public List<Reservation> findReservationsByRoomIdRoomId(Integer roomId) {
        return reservationRepository.findReservationsByRoomIdRoomId(roomId);
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
