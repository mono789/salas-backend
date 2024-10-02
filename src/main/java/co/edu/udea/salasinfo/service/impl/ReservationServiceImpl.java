package co.edu.udea.salasinfo.service.impl;

import co.edu.udea.salasinfo.dto.request.ClassReservationRequest;
import co.edu.udea.salasinfo.dto.request.ReservationRequest;
import co.edu.udea.salasinfo.dto.response.ReservationResponse;
import co.edu.udea.salasinfo.exceptions.EntityNotFoundException;
import co.edu.udea.salasinfo.exceptions.RoomOccupiedAtException;
import co.edu.udea.salasinfo.mapper.request.ReservationRequestMapper;
import co.edu.udea.salasinfo.mapper.response.ReservationResponseMapper;
import co.edu.udea.salasinfo.model.Reservation;
import co.edu.udea.salasinfo.model.ReservationState;
import co.edu.udea.salasinfo.persistence.ReservationDAO;
import co.edu.udea.salasinfo.persistence.ReservationStateDAO;
import co.edu.udea.salasinfo.persistence.RoleDAO;
import co.edu.udea.salasinfo.service.ReservationService;
import co.edu.udea.salasinfo.utils.enums.RStatus;
import co.edu.udea.salasinfo.utils.enums.ReservationType;
import co.edu.udea.salasinfo.utils.enums.WeekDay;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationDAO reservationDAO;
    private final ReservationStateDAO reservationStateDAO;
    private final ReservationResponseMapper reservationResponseMapper;
    private final ReservationRequestMapper reservationRequestMapper;

    public List<ReservationResponse> findAll() {
        return reservationResponseMapper.toResponses(reservationDAO.findAll());
    }

    //buscar segun su room
    @Override
    public ReservationResponse findById(Long roomId) {
        return reservationResponseMapper.toResponse(reservationDAO.findById(roomId));
    }

    @Override
    public ReservationResponse save(ReservationRequest reservation) {
        Reservation entity = reservationRequestMapper.toEntity(reservation);
        if (reservationDAO.existsByStartsAtAndRoomId(entity.getStartsAt(), entity.getRoom()))
            throw new RoomOccupiedAtException(entity.getRoom().getId().toString(), entity.getStartsAt());

        entity.setReservationState(reservationStateDAO.findByState(RStatus.IN_REVISION));
        Reservation result = reservationDAO.save(entity);
        return reservationResponseMapper.toResponse(result);
    }

    @Override
    public List<ReservationResponse> saveClass(ClassReservationRequest classReservation) {
        List<Reservation> reservations = generateClassReservations(classReservation);
        reservations.forEach(reservation -> {
            if (reservationDAO.existsByStartsAtAndRoomId(reservation.getStartsAt(), reservation.getRoom()))
                throw new RoomOccupiedAtException(reservation.getRoom().getId().toString(), reservation.getStartsAt());
        });
        return reservationResponseMapper.toResponses(reservationDAO.saveAll(reservations));
    }

    //borrar una reserva de la DB con un id de reserva
    @Override
    public ReservationResponse delete(Long reservationId) {
        Reservation reservation = reservationDAO.findById(reservationId);
        reservationDAO.deleteById(reservationId);
        return reservationResponseMapper.toResponse(reservation);
    }

    //actualizar una reserva existente
    @Override
    public ReservationResponse update(Long id, ReservationRequest reservation) {
        //TODO: review update method
        Reservation entity = reservationDAO.findById(id);
        if (reservationDAO.existsById(id))
            throw new EntityNotFoundException(Reservation.class.getSimpleName(), id);
        entity.setReservationState(ReservationState.builder().state(RStatus.IN_REVISION).build());
        Reservation result = reservationDAO.save(entity);
        return reservationResponseMapper.toResponse(result);

    }

    @Override
    public ReservationResponse updateState(Long id, RStatus state) {
        Reservation reservation = reservationDAO.findById(id);

        //de lo contrario
        reservation.setReservationState(ReservationState.builder().state(state).build());
        Reservation result = reservationDAO.save(reservation);
        return reservationResponseMapper.toResponse(result);

    }

    @Override
    public List<ReservationResponse> findStated(RStatus state) {

        List<Reservation> reservations = reservationDAO.findAll();
        //lista de reservas
        List<Reservation> type = new ArrayList<>();
        //itero por las recervas
        reservations.forEach(reservation -> {
            if (reservation.getReservationState().getState().equals(state))
                type.add(reservation);

        });
        return reservationResponseMapper.toResponses(type);
    }


    /**
     * Takes a class reservation and creates reservations between its semester limits
     *
     * @param classReservation Reservation for all semester
     * @return Built reservations
     */
    private List<Reservation> generateClassReservations(ClassReservationRequest classReservation) {
        List<ReservationRequest> reservationRequests = new ArrayList<>();
        classReservation.getSessions().forEach(session -> {

            LocalDateTime startAt = classReservation.getSemesterStartAt()
                    .atTime(session.getStartsAt())
                    .with(TemporalAdjusters.next(mapWeekDay(session.getDay())));

            while (startAt.isBefore(classReservation.getSemesterEndsAt().atStartOfDay())) {
                ReservationRequest reservationRequest = reservationRequestMapper.toRequest(classReservation);
                reservationRequest.setStartsAt(startAt);
                reservationRequest.setEndsAt(startAt.toLocalDate().atTime(session.getEndsAt()));
                reservationRequest.setType(ReservationType.WEEKLY);
                reservationRequests.add(reservationRequest);
                startAt = startAt.plusWeeks(1L);
            }
        });

        ReservationState reservationState = reservationStateDAO.findByState(RStatus.ACCEPTED);
        return reservationRequestMapper.toEntities(reservationRequests).stream()
                .map(reservation -> {
                    reservation.setReservationState(reservationState);
                    return reservation;
                }).toList();
    }

    private DayOfWeek mapWeekDay(WeekDay day) {
        return switch (day) {
            case MONDAY -> DayOfWeek.MONDAY;
            case TUESDAY -> DayOfWeek.TUESDAY;
            case WEDNESDAY -> DayOfWeek.WEDNESDAY;
            case THURSDAY -> DayOfWeek.THURSDAY;
            case FRIDAY -> DayOfWeek.FRIDAY;
            case SATURDAY -> DayOfWeek.SATURDAY;
        };
    }
}



