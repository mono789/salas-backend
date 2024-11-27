package co.edu.udea.salasinfo.persistence.jpa;

import co.edu.udea.salasinfo.exceptions.ReservationNotFoundException;
import co.edu.udea.salasinfo.model.Reservation;
import co.edu.udea.salasinfo.model.Room;
import co.edu.udea.salasinfo.model.User;
import co.edu.udea.salasinfo.repository.ReservationRepository;
import co.edu.udea.salasinfo.utils.enums.ReservationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationJPATest {

    @InjectMocks
    private ReservationJPA reservationJPA;

    @Mock
    private ReservationRepository reservationRepository;

    private Reservation reservation;

    @BeforeEach
    void setUp() {
        User user = User.builder().id(String.valueOf(1L)).build(); // Assuming a User has a builder
        Room room = Room.builder().id(1L).build(); // Assuming Room has a builder
        reservation = Reservation.builder()
                .id(1L)
                .activityName("Meeting")
                .activityDescription("Project meeting")
                .startsAt(LocalDateTime.now())
                .endsAt(LocalDateTime.now().plusHours(1))
                .type(ReservationType.WEEKLY) // Assuming you have an enum ReservationType
                .user(user)
                .room(room)
                .build();
    }

    @Test
    void testSave() {
        // Arrange
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        // Act
        Reservation savedReservation = reservationJPA.save(reservation);

        // Assert
        assertNotNull(savedReservation);
        assertEquals(reservation.getId(), savedReservation.getId());
        verify(reservationRepository).save(reservation);
    }

    @Test
    void testFindAll() {
        // Arrange
        when(reservationRepository.findAll()).thenReturn(Collections.singletonList(reservation));

        // Act
        List<Reservation> reservations = reservationJPA.findAll();

        // Assert
        assertNotNull(reservations);
        assertEquals(1, reservations.size());
        assertEquals(reservation.getId(), reservations.get(0).getId());
        verify(reservationRepository).findAll();
    }

    @Test
    void testFindById_Success() {
        // Arrange
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        // Act
        Reservation foundReservation = reservationJPA.findById(1L);

        // Assert
        assertNotNull(foundReservation);
        assertEquals(reservation.getId(), foundReservation.getId());
        verify(reservationRepository).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        // Arrange
        when(reservationRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        ReservationNotFoundException thrown = assertThrows(ReservationNotFoundException.class, () -> reservationJPA.findById(1L));

        assertEquals("Reservation with id '1' not found", thrown.getMessage());
        verify(reservationRepository).findById(1L);
    }

    @Test
    void testDeleteById() {
        // Arrange
        doNothing().when(reservationRepository).deleteById(1L);

        // Act
        reservationJPA.deleteById(1L);

        // Assert
        verify(reservationRepository).deleteById(1L);
    }

    @Test
    void testSaveAll() {
        // Arrange
        when(reservationRepository.saveAll(Collections.singletonList(reservation))).thenReturn(Collections.singletonList(reservation));

        // Act
        List<Reservation> savedReservations = reservationJPA.saveAll(Collections.singletonList(reservation));

        // Assert
        assertNotNull(savedReservations);
        assertEquals(1, savedReservations.size());
        assertEquals(reservation.getId(), savedReservations.get(0).getId());
        verify(reservationRepository).saveAll(Collections.singletonList(reservation));
    }

    @Test
    void testFindByType() {
        // Arrange
        when(reservationRepository.findByType(ReservationType.WEEKLY)).thenReturn(Collections.singletonList(reservation));

        // Act
        List<Reservation> reservationsByType = reservationJPA.findByType(ReservationType.WEEKLY);

        // Assert
        assertNotNull(reservationsByType);
        assertEquals(1, reservationsByType.size());
        assertEquals(reservation.getId(), reservationsByType.get(0).getId());
        verify(reservationRepository).findByType(ReservationType.WEEKLY);
    }

    @Test
    void testExistsById() {
        // Arrange
        when(reservationRepository.existsById(1L)).thenReturn(true);

        // Act
        boolean exists = reservationJPA.existsById(1L);

        // Assert
        assertTrue(exists);
        verify(reservationRepository).existsById(1L);
    }

    @Test
    void testFindReservationsByRoomId() {
        // Arrange
        when(reservationRepository.findReservationsByRoomId(1L)).thenReturn(Collections.singletonList(reservation));

        // Act
        List<Reservation> reservations = reservationJPA.findReservationsByRoomIdRoomId(1L);

        // Assert
        assertNotNull(reservations);
        assertEquals(1, reservations.size());
        assertEquals(reservation.getId(), reservations.get(0).getId());
        verify(reservationRepository).findReservationsByRoomId(1L);
    }
}