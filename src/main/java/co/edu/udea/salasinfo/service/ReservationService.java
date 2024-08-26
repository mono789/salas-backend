package co.edu.udea.salasinfo.service;

import co.edu.udea.salasinfo.dto.ReservationDTO;

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



