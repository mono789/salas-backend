package co.edu.udea.SalasInfo.Controller;

import org.junit.jupiter.api.BeforeEach;
import co.edu.udea.SalasInfo.Model.Reservation;
import co.edu.udea.SalasInfo.Service.ReservationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ReservationControllerTest {

    @Mock
    ReservationService reservationService;

    @InjectMocks
    ReservationController reservationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAll() {
        // Mocking the service response
        List<Reservation> reservations = Collections.singletonList(new Reservation());
        when(reservationService.findAll()).thenReturn(reservations);

        // Calling the controller method
        List<Reservation> result = reservationController.findAll();

        // Verifying that the controller returned the expected result
        assertEquals(reservations, result);
    }

    @Test
    void testAcceptReservation() {
        // Mocking the service response
        Reservation reservation = new Reservation();
        ResponseEntity<Reservation> responseEntity = new ResponseEntity<>(reservation, HttpStatus.OK);
        when(reservationService.updateState(reservation, 2)).thenReturn(responseEntity);

        // Calling the controller method
        ResponseEntity<Reservation> result = reservationController.acceptReservation(reservation);

        // Verifying that the controller returned the expected result
        assertEquals(responseEntity, result);
    }

    @Test
    void testRejectReservation() {
        // Mocking the service response
        Reservation reservation = new Reservation();
        ResponseEntity<Reservation> responseEntity = new ResponseEntity<>(reservation, HttpStatus.OK);
        when(reservationService.updateState(reservation, 1)).thenReturn(responseEntity);

        // Calling the controller method
        ResponseEntity<Reservation> result = reservationController.rejectReservation(reservation);

        // Verifying that the controller returned the expected result
        assertEquals(responseEntity, result);
    }

    @Test
    void testFreeAll() {
        // Mocking the service response
        List<Reservation> reservations = Collections.singletonList(new Reservation());
        String hora = "2023-10-25T15:30:00.000+00:00";
        when(reservationService.freeAll(hora)).thenReturn(reservations);

        // Calling the controller method
        List<Reservation> result = reservationController.freeAll(hora);

        // Verifying that the controller returned the expected result
        assertEquals(reservations, result);
    }

    @Test
    void testSave() {
        // Mocking the service response
        Reservation reservation = new Reservation();
        ResponseEntity<Reservation> responseEntity = new ResponseEntity<>(reservation, HttpStatus.OK);
        when(reservationService.save(reservation)).thenReturn(responseEntity);

        // Calling the controller method
        ResponseEntity<Reservation> result = reservationController.save(reservation);

        // Verifying that the controller returned the expected result
        assertEquals(responseEntity, result);
    }

    @Test
    void testFindById() {
        // Mocking the service response
        Integer id = 1;
        Reservation reservation = new Reservation();
        ResponseEntity<Reservation> responseEntity = new ResponseEntity<>(reservation, HttpStatus.OK);
        when(reservationService.findById(id)).thenReturn(responseEntity);

        // Calling the controller method
        ResponseEntity<Reservation> result = reservationController.findById(id);

        // Verifying that the controller returned the expected result
        assertEquals(responseEntity, result);
    }

    @Test
    void testDelete() {
        // Mocking the service response
        Integer id = 1;
        ResponseEntity<Reservation> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        when(reservationService.delete(id)).thenReturn(responseEntity);

        // Calling the controller method
        ResponseEntity<Reservation> result = reservationController.delete(id);

        // Verifying that the controller returned the expected result
        assertEquals(responseEntity, result);
    }

    @Test
    void testUpdate() {
        // Mocking the service response
        Reservation reservation = new Reservation();
        ResponseEntity<Reservation> responseEntity = new ResponseEntity<>(reservation, HttpStatus.OK);
        when(reservationService.update(reservation)).thenReturn(responseEntity);

        // Calling the controller method
        ResponseEntity<Reservation> result = reservationController.update(reservation);

        // Verifying that the controller returned the expected result
        assertEquals(responseEntity, result);
    }

    @Test
    void testFindByRoomId() {
        // Mocking the service response
        Integer roomId = 1;
        List<Reservation> reservations = Collections.singletonList(new Reservation());
        ResponseEntity<List<Reservation>> responseEntity = new ResponseEntity<>(reservations, HttpStatus.OK);
        when(reservationService.findReservationByRoomId(roomId)).thenReturn(responseEntity);

        // Calling the controller method
        ResponseEntity<List<Reservation>> result = reservationController.findByRoomId(roomId);

        // Verifying that the controller returned the expected result
        assertEquals(responseEntity, result);
    }
    @Test
    void testFindRefused() {
        // Mocking the service response
        List<Reservation> reservations = Collections.singletonList(new Reservation());
        when(reservationService.findStated(1)).thenReturn(reservations);

        // Calling the controller method
        List<Reservation> result = reservationController.findRefused();

        // Verifying that the controller returned the expected result
        assertEquals(reservations, result);
    }

    @Test
    void testFindAccept() {
        // Mocking the service response
        List<Reservation> reservations = Collections.singletonList(new Reservation());
        when(reservationService.findStated(2)).thenReturn(reservations);

        // Calling the controller method
        List<Reservation> result = reservationController.findAccept();

        // Verifying that the controller returned the expected result
        assertEquals(reservations, result);
    }

    @Test
    void testFindRevision() {
        // Mocking the service response
        List<Reservation> reservations = Collections.singletonList(new Reservation());
        when(reservationService.findStated(3)).thenReturn(reservations);

        // Calling the controller method
        List<Reservation> result = reservationController.findRevision();

        // Verifying that the controller returned the expected result
        assertEquals(reservations, result);
    }
}
