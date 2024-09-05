package co.edu.udea.salasinfo.service.impl;

import co.edu.udea.salasinfo.dto.response.room.RoomResponse;
import co.edu.udea.salasinfo.mapper.response.RoomResponseMapper;
import co.edu.udea.salasinfo.persistence.ApplicationDAO;
import co.edu.udea.salasinfo.model.Application;
import co.edu.udea.salasinfo.model.Room;
import co.edu.udea.salasinfo.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationDAO applicationDAO;
    private final RoomResponseMapper roomResponseMapper;


    //retorna una lista de salones, recibe una lista(string) de application(columna applicationName)
    //recibo una lista de string(Applications) y capturo sus id-->retorno una lista de id
    public List<RoomResponse> applicationMatch(List<String> applicationNames) {
        List<Application> allApplications = applicationDAO.findAll();

        // Mapear los nombres de Application a sus identificadores
        List<Long> applicationIds = applicationNames.stream()
                .map(name -> {
                    Application application = allApplications.stream()
                            .filter(app -> app.getName().equals(name))
                            .findFirst()
                            .orElse(null);

                    return application != null ? application.getId() : null;
                })
                .filter(Objects::nonNull)
                        .toList();

        return roomsApplications(applicationIds);
    }

    private List<RoomResponse> roomsApplications(List<Long> applicationIds) {
        // Lista para almacenar las salas que coinciden con todos las apps
        List<Room> matchedRooms = new ArrayList<>();

        // Conjunto para almacenar las salas que coinciden con cada app
        Set<Room> tempRooms = new HashSet<>();

        // Iterar sobre cada ID de app
        for (Long applicationId : applicationIds) {
            Application application = applicationDAO.findById(applicationId);
            if (application != null) {
                // Obtener las salas que contienen esta app
                List<Room> rooms = applicationDAO.findRoomsByApplicationId(application.getId());
                tempRooms.addAll(rooms);

            }
        }


        Map<Long, Integer> idCountMap = new HashMap<>();
        Set<Long> addedRoomIds = new HashSet<>();
        // Contar la frecuencia de cada ID en tempRooms
        for (Room room : tempRooms) {
            Long roomId = room.getId();
            idCountMap.put(roomId, idCountMap.getOrDefault(roomId, 0) + 1);
        }
        // Agregar solo los salones cuyo ID aparece N veces, donde N es la cantidad de appsIds
        for (Room room : tempRooms) {
            Long roomId = room.getId();
            if (idCountMap.get(roomId) == applicationIds.size() && !addedRoomIds.contains(roomId)) {
                addedRoomIds.add(roomId);
                matchedRooms.add(room);
            }
        }
        return roomResponseMapper.toResponses(matchedRooms);
    }



}



