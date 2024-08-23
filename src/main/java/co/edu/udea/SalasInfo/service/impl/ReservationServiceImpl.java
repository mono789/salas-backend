package co.edu.udea.SalasInfo.service.impl;

import co.edu.udea.SalasInfo.exceptions.EntityNotFoundException;
import co.edu.udea.SalasInfo.dto.ReservationDTO;
import co.edu.udea.SalasInfo.mapper.ReservationDTOMapper;
import co.edu.udea.SalasInfo.model.Reservation;
import co.edu.udea.SalasInfo.model.ReservationState;
import co.edu.udea.SalasInfo.persistence.ReservationDAO;
import co.edu.udea.SalasInfo.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationDAO reservationDAO;
    private final ReservationDTOMapper reservationDTOMapper;

    public List<ReservationDTO> findAll() {
        //capturar y enviar los elementos de la bas de datos que tengan reservationstateid=2
        return findStated(2);

    }

    //Lista de salones que estan libre en una hora especifica
    public List<ReservationDTO> freeAll(String hora1) {
        //formato como se recibe la hora-->  2023-10-25T15:30:00.000+00:00

        //capturo todas las reservas
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        // Convierte el String a OffsetDateTime
        OffsetDateTime hora = OffsetDateTime.parse(hora1, formato);
        List<Reservation> reservations1 = reservationDAO.findAll();
        //comparo solo con las recervas aceptadas
        List<Reservation> reservations = new ArrayList<>();
        //itero por las recervas
        for (Reservation reservation : reservations1) {
            if (Objects.equals(reservation.getReservationStateId().getReservationStateId(), 2)) {
                reservations.add(reservation);
            }
        }
        //lista de salones que no estan dentro de la consulta de reservas

        List<Reservation> free = new ArrayList<>();
        // evaluo si la fecha que paso se encuentra entre las fechas de ese dia horas de startsAt o endsAt
        for (Reservation reservation : reservations) {
            if (reservation.getStartsAt() != null) {
                LocalDateTime fechaReserva = reservation.getStartsAt();

                //System.out.println(j[0]);
                if (!fechaReserva.equals(hora.toLocalDateTime())) {
                    //evaluo si el salon esta libre en  esa Hora
                    LocalTime horaInicio = LocalTime.of(reservation.getStartsAt().getHour(), reservation.getStartsAt().getMinute());
                    LocalTime horaFin = LocalTime.of(reservation.getEndsAt().getHour(), reservation.getEndsAt().getMinute());

                    LocalTime horaFecha = hora.toLocalTime();
                    if (!(horaFecha.isAfter(horaInicio) && horaFecha.isBefore(horaFin))) {
                        free.add(reservation);
                    }
                } else {
                    free.add(reservation);
                }
            }
        }
        return reservationDTOMapper.toDTOs(free);
    }

    //buscar segun su room
    public ReservationDTO findById(Integer room_id) {
        //usamos siempre un optional para no tratar con excepciones de alcance por id, debido a un salon incorrecto

        return reservationDTOMapper.toDTO(reservationDAO.findById(room_id));
    }

    //crear un Nuevo elemento
    public ReservationDTO save(ReservationDTO reservation) {
        Reservation entity = reservationDTOMapper.toEntity(reservation);
        Reservation foundReservation = reservationDAO.findFirstByStartsAtAndRoomId(entity.getStartsAt(), entity.getRoomId());
        entity.setReservationId(3);//in revision
        Reservation result = reservationDAO.save(entity);
        return reservationDTOMapper.toDTO(result);
    }

    //borrar una reserva de la DB con un id de reserva
    public ReservationDTO delete(Integer reservationId) {
        Reservation reservation = reservationDAO.findById(reservationId);
        reservationDAO.deleteById(reservationId);
        return reservationDTOMapper.toDTO(reservation);
    }

    //actualizar una reserva existente
    public ReservationDTO update(ReservationDTO reservation) {
        Reservation entity = reservationDTOMapper.toEntity(reservation);

        if (reservationDAO.existsById(entity.getReservationId()))
            throw new EntityNotFoundException(Reservation.class.getSimpleName(), reservation.getReservationId());
        entity.setReservationStateId(new ReservationState(3));
        Reservation result = reservationDAO.save(entity);
        return reservationDTOMapper.toDTO(entity);

    }

    public ReservationDTO updateState(ReservationDTO reservation, Integer state) {
        Reservation entity = reservationDTOMapper.toEntity(reservation);
        if (!reservationDAO.existsById(reservation.getReservationId()))
            throw new EntityNotFoundException(Reservation.class.getSimpleName(), reservation.getReservationId());

        //de lo contrario
        entity.setReservationStateId(new ReservationState(state));
        Reservation result = reservationDAO.save(entity);
        return reservationDTOMapper.toDTO(result);

    }

    public List<ReservationDTO> findStated(@PathVariable Integer state) {

        List<Reservation> reservations = reservationDAO.findAll();
        //lista de reservas
        List<Reservation> type = new ArrayList<>();
        //itero por las recervas
        for (Reservation reservation : reservations) {
            if (Objects.equals(reservation.getReservationStateId().getReservationStateId(), state)) {
                type.add(reservation);
            }
        }
        return reservationDTOMapper.toDTOs(reservations);
    }


    /**
     * Search all the reservations of a room using a given roomId
     *
     * @param roomId the id of the room.
     * @return a Response entity with the found Reservations and 200 as status code.
     */
    public List<ReservationDTO> findReservationByRoomId(Integer roomId) {
        List<Reservation> reservations = reservationDAO.findReservationsByRoomIdRoomId(roomId);

        //retorno solo las aceptadas State ==2
        List<Reservation> type = new ArrayList<>();
        //itero por las recervas
        for (Reservation reservation : reservations) {
            if (Objects.equals(reservation.getReservationStateId().getReservationStateId(), 2)) {
                type.add(reservation);
            }
        }

        return reservationDTOMapper.toDTOs(type);
    }

    /**
     * Updates start and end dates of the class Reservations every week
     * if they have the date before the current day.
     */
    @Scheduled(fixedRate = 7 * 24 * 60 * 60 * 1000) // Every week
    public void updateClassDates() {
        // Retrieve the list of class reservations
        List<Reservation> classes = reservationDAO.findByReservationType(1);

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



