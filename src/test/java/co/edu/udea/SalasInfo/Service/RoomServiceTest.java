package co.edu.udea.SalasInfo.Service;

import co.edu.udea.SalasInfo.DAO.RoomRepository;
import co.edu.udea.SalasInfo.Model.Application;
import co.edu.udea.SalasInfo.Model.Implement;
import co.edu.udea.SalasInfo.Model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class RoomServiceTest {

    @Mock
    RoomRepository roomRepository;
    @InjectMocks
    RoomService roomService;
    private Room room;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Creating a mock room
        room = new Room();
        room.setComputerAmount(50);
        room.setBuilding("22");
        room.setRoomNum("325");
        room.setSubRoom(0);
        Application app = new Application();
        app.setApplicationId(1);
        app.setApplicationName("Gogle draiv");
        Implement impl = new Implement(1, "Projector", "Good");
        room.setSoftware(List.of(app));
        room.setImplementList(List.of(impl));
    }

    @Test
    void findAll() {
        List<Room> rooms = Collections.singletonList(room);
        Mockito.when(roomRepository.findAll()).thenReturn(rooms);
        assertNotNull(roomService.findAll());
    }

    @Test
    void findById() {
        Mockito.when(roomRepository.findById(223250)).thenReturn(Optional.ofNullable(room));
        assertNotNull(roomService.findById(223250));
    }

    @Test
    void createRoom() {
        Mockito.when(roomRepository.save(any(Room.class))).thenReturn(room);

        assertNotNull(roomService.createRoom(room));
    }

    @Test
    void updateRoom() {
        Room body = new Room();
        body.setRoomName("Updated");
        body.setRoomId(223250);
        Mockito.when(roomRepository.findById(223250)).thenReturn(Optional.of(room));
        Mockito.when(roomRepository.save(any(Room.class))).thenReturn(body);

        Room updated = roomService.updateRoom(body).getBody();
        assertNotNull(updated);
        assertEquals("Updated", updated.getRoomName());
    }

    @Test
    void deleteRoom() {
        Mockito.when(roomRepository.findById(223250)).thenReturn(Optional.of(room));
        roomService.deleteRoom(223250);
        Mockito.verify(roomRepository).deleteById(223250);
        assertEquals(room, roomService.deleteRoom(223250).getBody());
    }

    @Test
    void findRoomsBySoftwareId(){
        Application app = new Application();
        app.setApplicationId(1);
        Mockito.when(roomRepository.findRoomsBySoftwareContaining(app)).thenReturn(Collections.singletonList(room));
        List<Room> retrievedRooms = roomService.findRoomsBySoftwareId(1).getBody();
        assert retrievedRooms != null;
        assertEquals(room, retrievedRooms.get(0));
    }

    @Test
    void findRoomsByImplementId(){
        Implement implement = new Implement();
        implement.setImplementId(1);
        Mockito.when(roomRepository.findRoomsByImplementListContaining(implement)).thenReturn(Collections.singletonList(room));
        List<Room> retrievedRooms = roomService.findRoomsByImplementId(1).getBody();
        assert retrievedRooms != null;
        assertEquals(room, retrievedRooms.get(0));
    }
}