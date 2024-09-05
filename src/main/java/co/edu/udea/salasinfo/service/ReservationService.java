package co.edu.udea.salasinfo.service;

import co.edu.udea.salasinfo.dto.request.ReservationRequest;
import co.edu.udea.salasinfo.dto.response.ReservationResponse;
import co.edu.udea.salasinfo.utils.enums.RState;

import java.util.*;

public interface ReservationService {
    List<ReservationResponse> findAll();

    List<ReservationResponse> freeAll(String hour);

    ReservationResponse findById(Long id);

    ReservationResponse save(ReservationRequest reservation);

    ReservationResponse delete(Long reservationId);

    ReservationResponse update(Long id, ReservationRequest reservation);

    ReservationResponse updateState(Long id, Long state);
    List<ReservationResponse> findStated(RState state);
}



