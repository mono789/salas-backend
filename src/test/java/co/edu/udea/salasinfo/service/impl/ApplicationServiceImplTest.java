package co.edu.udea.salasinfo.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import co.edu.udea.salasinfo.dto.response.room.RoomResponse;
import co.edu.udea.salasinfo.mapper.response.RoomResponseMapper;
import co.edu.udea.salasinfo.model.Application;
import co.edu.udea.salasinfo.model.Room;
import co.edu.udea.salasinfo.persistence.ApplicationDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class ApplicationServiceImplTest {

    @InjectMocks
    private ApplicationServiceImpl applicationService;

    @Mock
    private ApplicationDAO applicationDAO;

    @Mock
    private RoomResponseMapper roomResponseMapper;

    private List<Application> allApplications;

    private List<Room> roomsForApplication1;
    private List<Room> roomsForApplication2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize mock data
        Application app1 = new Application(1L, "App1", null);
        Application app2 = new Application(2L, "App2", null);

        allApplications = Arrays.asList(app1, app2);

        Room room1 = Room.builder().id(1L).roomName("Room1").build();
        Room room2 = Room.builder().id(2L).roomName("Room2").build();

        roomsForApplication1 = Arrays.asList(room1, room2);  // Rooms that have App1
        roomsForApplication2 = Collections.singletonList(room1);  // Only Room1 has both App1 and App2
    }

    @Test
    void applicationMatch_success() {
        // Mock findAll to return the mock applications
        when(applicationDAO.findAll()).thenReturn(allApplications);

        // Mock findRoomsByApplicationId for each application
        when(applicationDAO.findRoomsByApplicationId(1L)).thenReturn(roomsForApplication1);
        when(applicationDAO.findRoomsByApplicationId(2L)).thenReturn(roomsForApplication2);

        // Expected matching room (Room1 has both applications)
        RoomResponse roomResponse = new RoomResponse(1, 1, "Room1", "Room", "Room", 0);
        when(roomResponseMapper.toResponses(anyList())).thenReturn(List.of(roomResponse));

        List<String> applicationNames = Arrays.asList("App1", "App2");
        List<RoomResponse> result = applicationService.applicationMatch(applicationNames);

        // Verify the result contains only Room1
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void applicationMatch_noMatchingRoom() {
        // Mock findAll to return the mock applications
        when(applicationDAO.findAll()).thenReturn(allApplications);

        // Mock findRoomsByApplicationId for each application
        when(applicationDAO.findRoomsByApplicationId(1L)).thenReturn(roomsForApplication1);
        when(applicationDAO.findRoomsByApplicationId(2L)).thenReturn(new ArrayList<>()); // No rooms for App2

        List<String> applicationNames = Arrays.asList("App1", "App2");
        List<RoomResponse> result = applicationService.applicationMatch(applicationNames);

        // No rooms should match both applications
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void applicationMatch_applicationNotFound() {
        // Mock findAll to return applications with only App1
        Application app1 = new Application(1L, "App1", null);
        when(applicationDAO.findAll()).thenReturn(List.of(app1));

        // Return rooms for App1 but fail on App2
        when(applicationDAO.findRoomsByApplicationId(1L)).thenReturn(roomsForApplication1);

        List<String> applicationNames = Arrays.asList("App1", "App2");
        List<RoomResponse> result = applicationService.applicationMatch(applicationNames);

        // Verify that no room matches since App2 does not exist
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}