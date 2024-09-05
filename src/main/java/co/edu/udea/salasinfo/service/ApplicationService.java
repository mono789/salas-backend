package co.edu.udea.salasinfo.service;

import co.edu.udea.salasinfo.dto.response.room.RoomResponse;

import java.util.*;

public interface ApplicationService {
    List<RoomResponse> applicationMatch(List<String> applicationNames);
}



