package co.edu.udea.SalasInfo.Controller;

import co.edu.udea.SalasInfo.Model.Room;
import co.edu.udea.SalasInfo.Service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoomController.class)
class RoomControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RoomService roomService;
    private Room room;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        room = new Room(191020, 30, "19", "102", "Test", 0);
    }

    @Test
    void getAll() throws Exception {
        List<Room> rooms = Collections.singletonList(room);
        Mockito.when(roomService.findAll()).thenReturn(ResponseEntity.ok(rooms));
        mockMvc.perform(get("/room/find-all")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].roomId").value(room.getRoomId()))
            .andExpect(jsonPath("$[0].computerAmount").value(room.getComputerAmount()))
            .andExpect(jsonPath("$[0].building").value(room.getBuilding()))
            .andExpect(jsonPath("$[0].roomNum").value(room.getRoomNum()))
            .andExpect(jsonPath("$[0].roomName").value(room.getRoomName()))
            .andExpect(jsonPath("$[0].subRoom").value(room.getSubRoom()));
    }

    @Test
    void save() throws Exception {
        Room roomPost = new Room(null, 30, "19", "102", "Test", 0);
        Mockito.when(roomService.createRoom(roomPost)).thenReturn(ResponseEntity.ok(room));
        mockMvc.perform(post("/room/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                {
                                    "computerAmount": 30,
                                    "building": "22",
                                    "roomNum": "102",
                                    "roomName": "Test",
                                    "subRoom": 0
                                }"""))
                .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        Mockito.when(roomService.findById(191020)).thenReturn(ResponseEntity.ok(room));
        mockMvc.perform(get("/room/find-by-id/191020")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomId").value(room.getRoomId()))
                .andExpect(jsonPath("$.building").value(room.getBuilding()))
                .andExpect(jsonPath("$.roomNum").value(room.getRoomNum()))
                .andExpect(jsonPath("$.subRoom").value(room.getSubRoom()));
    }

    @Test
    void remove() throws Exception {
        Mockito.when(roomService.deleteRoom(191020)).thenReturn(ResponseEntity.ok(room));
        mockMvc.perform(delete("/room/delete/191020")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomId").value(room.getRoomId()))
                .andExpect(jsonPath("$.building").value(room.getBuilding()))
                .andExpect(jsonPath("$.roomNum").value(room.getRoomNum()))
                .andExpect(jsonPath("$.subRoom").value(room.getSubRoom()));
    }

    @Test
    void update() throws Exception {
        Room body = new Room(191020, 50, null, null, "Updated", null);
        Room updatedRoom = new Room(191020, 50, "19", "102", "Updated", 0);
        Mockito.when(roomService.updateRoom(body)).thenReturn(ResponseEntity.ok(updatedRoom));
        mockMvc.perform(put("/room/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                {
                                    "roomId": 191020,
                                    "computerAmount": 50,
                                    "roomName": "Updated"
                                }"""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomId").value(room.getRoomId()))
                .andExpect(jsonPath("$.building").value(updatedRoom.getBuilding()))
                .andExpect(jsonPath("$.roomNum").value(updatedRoom.getRoomNum()))
                .andExpect(jsonPath("$.subRoom").value(updatedRoom.getSubRoom()));
    }
}