package co.edu.udea.salasinfo.persistence.jpa;

import co.edu.udea.salasinfo.exceptions.EntityNotFoundException;
import co.edu.udea.salasinfo.model.ReservationState;
import co.edu.udea.salasinfo.repository.ReservationStateRepository;
import co.edu.udea.salasinfo.utils.enums.RStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationStateJPATest {

    @InjectMocks
    private ReservationStateJPA reservationStateJPA;

    @Mock
    private ReservationStateRepository reservationStateRepository;

    private ReservationState reservationState;

    @BeforeEach
    void setUp() {
        reservationState = ReservationState.builder()
                .id(1L)
                .state(RStatus.ACCEPTED) // Assuming we want to test the ACCEPTED state
                .build();
    }

    @Test
    void testFindByState_Success() {
        // Arrange
        when(reservationStateRepository.findByState(RStatus.ACCEPTED)).thenReturn(Optional.of(reservationState));

        // Act
        ReservationState foundState = reservationStateJPA.findByState(RStatus.ACCEPTED);

        // Assert
        assertNotNull(foundState);
        assertEquals(reservationState.getId(), foundState.getId());
        assertEquals(RStatus.ACCEPTED, foundState.getState());
        verify(reservationStateRepository).findByState(RStatus.ACCEPTED);
    }

    @Test
    void testFindByState_NotFound() {
        // Arrange
        when(reservationStateRepository.findByState(RStatus.PENDING)).thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> reservationStateJPA.findByState(RStatus.PENDING));

        assertEquals("Entity of 'ReservationState' type searched with 'PENDING' not found", thrown.getMessage());
        verify(reservationStateRepository).findByState(RStatus.PENDING);
    }
}
