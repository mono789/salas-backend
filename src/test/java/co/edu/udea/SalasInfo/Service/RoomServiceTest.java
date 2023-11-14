package co.edu.udea.SalasInfo.Service;

import co.edu.udea.SalasInfo.DAO.RoomRepository;
import co.edu.udea.SalasInfo.Model.Application;
import co.edu.udea.SalasInfo.Model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
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
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        room = new Room();
        room.setComputerAmount(50);
        room.setBuilding("22");
        room.setRoomNum("325");
        room.setSubRoom(0);
    }

    @Test
    public void findAll() {
        List<Room> rooms = Collections.singletonList(room);
        Mockito.when(roomRepository.findAll()).thenReturn(rooms);
        assertNotNull(roomRepository.findAll());
    }

    @Test
    public void findById() {
        Mockito.when(roomRepository.findById(223250)).thenReturn(Optional.ofNullable(room));
        assertNotNull(roomRepository.findById(223250));
    }

    @Test
    public void createRoom() {
        Mockito.when(roomRepository.save(any(Room.class))).thenReturn(room);
        assertNotNull(roomRepository.save(new Room()));
    }

    @Test
    public void updateRoom() {
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
    public void deleteRoom() {
        Mockito.when(roomRepository.findById(223250)).thenReturn(Optional.of(room));
        roomService.deleteRoom(223250);
        Mockito.verify(roomRepository).deleteById(223250);
        assertEquals(room, roomService.deleteRoom(223250).getBody());
    }

}