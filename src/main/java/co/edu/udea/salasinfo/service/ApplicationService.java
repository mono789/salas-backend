package co.edu.udea.salasinfo.service;

import co.edu.udea.salasinfo.dto.RoomDTO;

import java.util.*;

public interface ApplicationService {
    List<RoomDTO> applicationMatch(List<String> applicationNames);
}



