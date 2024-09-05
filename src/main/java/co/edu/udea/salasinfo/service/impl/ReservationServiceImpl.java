package co.edu.udea.salasinfo.service.impl;

import co.edu.udea.salasinfo.dto.request.ReservationRequest;
import co.edu.udea.salasinfo.dto.response.ReservationResponse;
import co.edu.udea.salasinfo.exceptions.EntityNotFoundException;
import co.edu.udea.salasinfo.mapper.request.ReservationRequestMapper;
import co.edu.udea.salasinfo.mapper.response.ReservationResponseMapper;
import co.edu.udea.salasinfo.model.Reservation;
import co.edu.udea.salasinfo.model.ReservationState;
import co.edu.udea.salasinfo.persistence.ReservationDAO;
import co.edu.udea.salasinfo.service.ReservationService;
import co.edu.udea.salasinfo.utils.enums.RState;
import co.edu.udea.salasinfo.utils.enums.ReservationType;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationDAO reservationDAO;
    private final ReservationResponseMapper reservationResponseMapper;
    private final ReservationRequestMapper reservationRequestMapper;

    public List<ReservationResponse> findAll() {
        return findStated(RState.ACCEPTED);

    }

    //Lista de salones que estan libre en una hora especifica
    @Override
    public List<ReservationResponse> freeAll(String hour) {
        //formato como se recibe la hora-->  2023-10-25T15:30:00.000+00:00

        //capturo todas las reservas
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        // Convierte el String a OffsetDateTime
        OffsetDateTime hora = OffsetDateTime.parse(hour, formato);
        List<Reservation> reservations = reservationDAO.findAll();
        //comparo solo con las recervas aceptadas
        List<Reservation> filteredReservations = reservations.stream()
                .filter(reservation -> reservation.getReservationState().getState().equals(RState.ACCEPTED)).toList();
        //lista de salones que no estan dentro de la consulta de reservas
        List<Reservation> free = new ArrayList<>();

        // evaluo si la fecha que paso se encuentra entre las fechas de ese dia horas de startsAt o endsAt

        filteredReservations.forEach(reservation -> {
            if (reservation.getStartsAt() != null) {
                LocalDateTime fechaReserva = reservation.getStartsAt();

                if (!fechaReserva.equals(hora.toLocalDateTime())) {
                    //evaluo si el salon esta libre en  esa Hora
                    LocalTime startTime = LocalTime.of(reservation.getStartsAt().getHour(), reservation.getStartsAt().getMinute());
                    LocalTime endTime = LocalTime.of(reservation.getEndsAt().getHour(), reservation.getEndsAt().getMinute());

                    LocalTime horaFecha = hora.toLocalTime();
                    if (!(horaFecha.isAfter(startTime) && horaFecha.isBefore(endTime))) {
                        free.add(reservation);
                    }
                } else {
                    free.add(reservation);
                }
            }
        });
        return reservationResponseMapper.toResponses(free);
    }

    //buscar segun su room
    @Override
    public ReservationResponse findById(Long roomId) {
        return reservationResponseMapper.toResponse(reservationDAO.findById(roomId));
    }

    //crear un Nuevo elemento
    @Override
    public ReservationResponse save(ReservationRequest reservation) {
        Reservation entity = reservationRequestMapper.toEntity(reservation);
        reservationDAO.findFirstByStartsAtAndRoomId(entity.getStartsAt(), entity.getRoom());
        entity.setReservationState(ReservationState.builder().state(RState.IN_REVISION).build());//in revision
        Reservation result = reservationDAO.save(entity);
        return reservationResponseMapper.toResponse(result);
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
        entity.setReservationState(ReservationState.builder().reservationStateId(3L).build());
        Reservation result = reservationDAO.save(entity);
        return reservationResponseMapper.toResponse(result);

    }

    @Override
    public ReservationResponse updateState(Long id, Long state) {
        Reservation reservation = reservationDAO.findById(id);

        //de lo contrario
        reservation.setReservationState(ReservationState.builder().reservationStateId(state).build());
        Reservation result = reservationDAO.save(reservation);
        return reservationResponseMapper.toResponse(result);

    }

    @Override
    public List<ReservationResponse> findStated(RState state) {

        List<Reservation> reservations = reservationDAO.findAll();
        //lista de reservas
        List<Reservation> type = new ArrayList<>();
        //itero por las recervas
        reservations.forEach(reservation -> {
            if(reservation.getReservationState().getState().equals(state))
                type.add(reservation);

        });
        return reservationResponseMapper.toResponses(type);
    }


    /**
     * Updates start and end dates of the class Reservations every week
     * if they have the date before the current day.
     */
    @Scheduled(fixedRate = 7 * 24 * 60 * 60 * 1000) // Every week
    public void updateClassDates() {
        // Retrieve the list of class reservations
        List<Reservation> classes = reservationDAO.findByType(ReservationType.WEEKLY);

        for (Reservation classReservation : classes) {
            // Get start and end class dates
            assert classReservation != null;
            LocalDateTime startDate = classReservation.getStartsAt();
            LocalDateTime endDate = classReservation.getEndsAt();

            // Analyze these dates, set the date to a new week and then update the object in database
            if (startDate.isBefore(LocalDateTime.now())) {
                // Only update if the class reservation was before today.
                classReservation.setStartsAt(
                        startDate.with(TemporalAdjusters.next(startDate.getDayOfWeek()))
                );
                classReservation.setEndsAt(
                        endDate.with(TemporalAdjusters.next(endDate.getDayOfWeek()))
                );
                reservationDAO.save(classReservation);
            }
        }
    }


}



