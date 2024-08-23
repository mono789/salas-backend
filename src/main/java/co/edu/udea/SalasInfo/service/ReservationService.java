package co.edu.udea.SalasInfo.service;

import co.edu.udea.SalasInfo.dto.ReservationDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

public interface ReservationService {
    List<ReservationDTO> findAll();

    List<ReservationDTO> freeAll(String hora1);

    ReservationDTO findById(Integer room_id);

    ReservationDTO save(ReservationDTO reservation);

    ReservationDTO delete(Integer reservationId);

    ReservationDTO update(ReservationDTO reservation);

    ReservationDTO updateState(ReservationDTO reservation, Integer state);

    List<ReservationDTO> findReservationByRoomId(Integer roomId);

    List<ReservationDTO> findStated(Integer state);

    void updateClassDates();
}



