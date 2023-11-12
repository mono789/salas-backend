package co.edu.udea.SalasInfo.Controller;

import co.edu.udea.SalasInfo.Model.Application;
import co.edu.udea.SalasInfo.Model.Room;
import co.edu.udea.SalasInfo.Service.RoomService;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoomController.class)
public class RoomControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RoomService roomService;
    private Room room;

    @BeforeEach
    void setUp() {
        room = new Room(191020, 30, "19", "102", "Test", 0);
        Application application = new Application(4, "Krita", "5.2.1");
        System.out.println(room);
    }

    @Test
    public void getAll() throws Exception {
        List<Room> rooms = Collections.singletonList(room);
        Mockito.when(roomService.findAll()).thenReturn(ResponseEntity.ok(rooms));
        mockMvc.perform(get("/api/room")
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
    public void save() throws Exception {
        Room roomPost = new Room(null, 30, "19", "102", "Test", 0);
        Mockito.when(roomService.createRoom(roomPost)).thenReturn(ResponseEntity.ok(room));
        mockMvc.perform(post("/api/room")
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
    public void findById() throws Exception {
        Mockito.when(roomService.findById(191020)).thenReturn(ResponseEntity.ok(room));
        mockMvc.perform(get("/api/room/191020")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomId").value(room.getRoomId()))
                .andExpect(jsonPath("$.building").value(room.getBuilding()))
                .andExpect(jsonPath("$.roomNum").value(room.getRoomNum()))
                .andExpect(jsonPath("$.subRoom").value(room.getSubRoom()));
    }

    @Test
    public void remove() throws Exception {
        Mockito.when(roomService.deleteRoom(191020)).thenReturn(ResponseEntity.ok(room));
        mockMvc.perform(delete("/api/room/191020")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomId").value(room.getRoomId()))
                .andExpect(jsonPath("$.building").value(room.getBuilding()))
                .andExpect(jsonPath("$.roomNum").value(room.getRoomNum()))
                .andExpect(jsonPath("$.subRoom").value(room.getSubRoom()));
    }

    @Test
    public void update() throws Exception {
        Room body = new Room(null, 50, null, null, "Updated", null);
        Room updatedRoom = new Room(191020, 50, "19", "102", "Updated", 0);
        Mockito.when(roomService.updateRoom(191020, body)).thenReturn(ResponseEntity.ok(updatedRoom));
        mockMvc.perform(put("/api/room/191020")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                {
                                    "computerAmount": 50,
                                    "roomName": "Updated"
                                }"""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomId").value(room.getRoomId()))
                .andExpect(jsonPath("$.building").value(updatedRoom.getBuilding()))
                .andExpect(jsonPath("$.roomNum").value(updatedRoom.getRoomNum()))
                .andExpect(jsonPath("$.subRoom").value(updatedRoom.getSubRoom()));
    }

    @Test
    public void findRoomSoftwareById() throws Exception {
        List<Application> foundSoftware= new ArrayList<>();
        foundSoftware.add(new Application(4, "Krita", "5.2.1"));
        foundSoftware.add(new Application(5, "IntelliJ", "2023.2"));
        room.setSoftware(foundSoftware);
        Mockito.when(roomService.findRoomSoftware(191020)).thenReturn(ResponseEntity.ok(foundSoftware));
        mockMvc.perform(get("/api/room/191020/software")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].applicationId").value(foundSoftware.get(0).getApplicationId()))
                .andExpect(jsonPath("$[1].applicationId").value(foundSoftware.get(1).getApplicationId()));
    }
}