package co.edu.udea.salasinfo.persistence.jpa;

import co.edu.udea.salasinfo.exceptions.EntityNotFoundException;
import co.edu.udea.salasinfo.model.Implement;
import co.edu.udea.salasinfo.model.Room;
import co.edu.udea.salasinfo.repository.ImplementRepository;
import co.edu.udea.salasinfo.utils.enums.ImplementCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImplementJPATest {

    @InjectMocks
    private ImplementJPA implementJPA;

    @Mock
    private ImplementRepository implementRepository;

    private Implement implement;

    @BeforeEach
    public void setUp() {
        implement = Implement.builder()
                .id(1L)
                .name("Projector")
                .rooms(Collections.emptyList())
                .build();
    }

    @Test
    void testFindById_Success() {
        // Arrange
        when(implementRepository.findById(1L)).thenReturn(Optional.of(implement));

        // Act
        Implement foundImplement = implementJPA.findById(1L);

        // Assert
        assertNotNull(foundImplement);
        assertEquals(implement.getId(), foundImplement.getId());
        verify(implementRepository).findById(1L);
    }

    @Test
    void testFindById_EntityNotFound() {
        // Arrange
        when(implementRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> implementJPA.findById(1L));

        assertEquals("Entity of 'Implement' type searched with '1' not found", thrown.getMessage());
        verify(implementRepository).findById(1L);
    }

    @Test
    void testFindAll() {
        // Arrange
        when(implementRepository.findAll()).thenReturn(Collections.singletonList(implement));

        // Act
        List<Implement> implementsList = implementJPA.findAll();

        // Assert
        assertNotNull(implementsList);
        assertEquals(1, implementsList.size());
        assertEquals(implement.getId(), implementsList.get(0).getId());
        verify(implementRepository).findAll();
    }

    @Test
    void testFindRoomsByImplementId() {
        // Arrange
        Room room1 = new Room(); // Assume Room class has a proper constructor
        room1.setId(1L);
        room1.setRoomName("Room A");

        implement.setRooms(List.of(room1));
        when(implementRepository.findById(1L)).thenReturn(Optional.of(implement));

        // Act
        List<Room> rooms = implementJPA.findRoomsByImplementId(1L);

        // Assert
        assertNotNull(rooms);
        assertEquals(1, rooms.size());
        assertEquals(room1.getId(), rooms.get(0).getId());
        verify(implementRepository).findById(1L);
    }
}
