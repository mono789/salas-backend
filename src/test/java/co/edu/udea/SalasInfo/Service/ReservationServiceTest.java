package co.edu.udea.SalasInfo.Service;

import co.edu.udea.SalasInfo.DAO.ReservationRepository;
import co.edu.udea.SalasInfo.Model.Reservation;
import co.edu.udea.SalasInfo.Model.ReservationState;
import co.edu.udea.SalasInfo.Model.Room;
import co.edu.udea.SalasInfo.Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReservationServiceTest {

     @Mock
     ReservationRepository reservationRepository;
     @InjectMocks
     private ReservationService reservationService;
     private Reservation reservation;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        reservation= new Reservation();
        reservation.setReservationId(10);//id de reserva
        reservation.setReservationStateId(new ReservationState());//un estado de reserva (1:clase, 2:reserva, 3:libre)
        Room room = new Room();
        room.setRoomId(123250);
        reservation.setRoomId(room);
        String cadenaFecha = "2023-11-03 10:30:00.0";
        String cadenaFecha2= "2023-11-03 12:30:00.0";
        // Define el formato de fecha
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        // Parsea la cadena de fecha y hora a un objeto java.util.Date
        Date date, date1;
        try {
             date = formatoFecha.parse(cadenaFecha);
             date1 = formatoFecha.parse(cadenaFecha2);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        // Convierte java.util.Date a java.sql.Timestamp
        Timestamp startAt = new Timestamp(date.getTime());
        Timestamp endsAt = new Timestamp(date1.getTime());
        reservation.setStartsAt(startAt.toLocalDateTime());
        reservation.setEndsAt(endsAt.toLocalDateTime());
        reservation.setReservationType(1);
        reservation.setActivityName("clase de 10");
        reservation.setUserId(new User());
        reservation.setActivityDescription("nada de raro");
    }

    @Test
    void findAll() {
        when(reservationRepository.findAll()).thenReturn(Collections.singletonList(reservation));
        assertNotNull(reservationService.findAll());
    }

    @Test
    void freeAll() {
        String horaConsulta = "2023-10-25T15:30:00.000+00:00";
        String horaConsulta1 = "2023-11-03T11:30:00.000+00:00";
        when(reservationRepository.findAll()).thenReturn(Collections.singletonList(reservation)); // primera llamada returna algo, la segunda no
        // Llama al método
        List<Reservation> salasLibres = reservationService.freeAll(horaConsulta);
        // Realiza aserciones
        assertEquals(1, salasLibres.size()); // Verifica que la lista tenga un elemento
        salasLibres = reservationService.freeAll(horaConsulta1);
        // Realiza aserciones
        assertEquals(0, salasLibres.size()); // Verifica que la lista esté vacía
    }
    @Test
    void findById() {
        when(reservationRepository.findById(1)).thenReturn(Optional.of(reservation));
        ResponseEntity<Reservation> response = reservationService.findById(1);
        //HTTP 200
        assertEquals(HttpStatus.OK, response.getStatusCode());
        response = reservationService.findById(2);
        //HTTP 404
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    void save() {
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);//cusnfo le mande un objeto de tipo reservation nos debe retornar una reserva
        assertNotNull(reservationService.save(new Reservation()));//no puede retornar nulo cuando llamemos esa funcion
    }

    @Test
    void delete() {
        when(reservationRepository.existsById(10)).thenReturn(true);
        doNothing().when(reservationRepository).deleteById(10);
        ResponseEntity<Reservation> deletedReservation = reservationService.delete(10);
        verify(reservationRepository).deleteById(10);
    }

    @Test
    void update() {
        Reservation updatedReservation = new Reservation();
        updatedReservation.setReservationId(10);
        when(reservationRepository.findById(10)).thenReturn(Optional.of(reservation));

        // Configura el comportamiento del mock para existsById
        when(reservationRepository.existsById(updatedReservation.getReservationId())).thenReturn(true);

        // Configura el comportamiento del mock para save
        when(reservationRepository.save(updatedReservation)).thenReturn(updatedReservation);

        // Llama al método update del servicio
        ResponseEntity<Reservation> response = reservationService.update(updatedReservation);

        // Verifica que la respuesta sea exitosa (HTTP 200)
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verifica que la reserva devuelta sea la misma que la configurada en el mock
        assertEquals(updatedReservation, response.getBody());
    }

    @Test
    void updateClassDates(){
        // Creating the updated List
        Reservation classReservation = new Reservation();
        classReservation.setReservationId(reservation.getReservationId());
        classReservation.setActivityName(reservation.getActivityName());
        classReservation.setActivityDescription(reservation.getActivityDescription());

        LocalDateTime startDate = reservation.getStartsAt();
        LocalDateTime endDate = reservation.getEndsAt();

        classReservation.setStartsAt(
                startDate.with(TemporalAdjusters.next(startDate.getDayOfWeek()))
        );

        classReservation.setEndsAt(
                endDate.with(TemporalAdjusters.next(endDate.getDayOfWeek()))
        );

        classReservation.setReservationType(reservation.getReservationType());
        classReservation.setRoomId(reservation.getRoomId());
        classReservation.setUserId(reservation.getUserId());
        classReservation.setReservationStateId(reservation.getReservationStateId());

        when(reservationRepository.findByReservationType(1)).thenReturn(Collections.singletonList(reservation));

        reservationService.updateClassDates();

        verify(reservationRepository, times(1)).save(classReservation);
    }

    @Test
    void findReservationByRoom() {
        List<Reservation> expectedReservations = Collections.singletonList(reservation);
        when(reservationRepository.findReservationsByRoomIdRoomId(123250))
                .thenReturn(expectedReservations);
        ResponseEntity<List<Reservation>> response = reservationService.findReservationByRoomId(123250);
        assertNotNull(response.getBody());
        Room retrievedReservationRoom = response.getBody().get(0).getRoomId();
        assertEquals(expectedReservations, response.getBody());
        assertEquals(123250, retrievedReservationRoom.getRoomId());
    }
}