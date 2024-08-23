package co.edu.udea.SalasInfo.service;

import co.edu.udea.SalasInfo.dto.RoomDTO;
import co.edu.udea.SalasInfo.model.Room;

import java.util.*;

public interface ApplicationService {
    List<RoomDTO> applicationMatch(List<String> applicationNames);
}



