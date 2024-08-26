package co.edu.udea.salasinfo.service.impl;

import co.edu.udea.salasinfo.dto.RoomDTO;
import co.edu.udea.salasinfo.mapper.RoomDTOMapper;
import co.edu.udea.salasinfo.persistence.ApplicationDAO;
import co.edu.udea.salasinfo.persistence.RoomDAO;
import co.edu.udea.salasinfo.model.Application;
import co.edu.udea.salasinfo.model.Room;
import co.edu.udea.salasinfo.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationDAO applicationDAO;
    private final RoomDAO roomDAO;
    private final RoomDTOMapper roomDTOMapper;


    //retorna una lista de salones, recibe una lista(string) de application(columna applicationName)
    //recibo una lista de string(Applications) y capturo sus id-->retorno una lista de id
    public List<RoomDTO> applicationMatch(List<String> applicationNames) {
        List<Application> allApplications = applicationDAO.findAll();

        // Mapear los nombres de Application a sus identificadores
        List<Integer> applicationIds = applicationNames.stream()
                .map(name -> {
                    Application application = allApplications.stream()
                            .filter(app -> app.getApplicationName().equals(name))
                            .findFirst()
                            .orElse(null);

                    return application != null ? application.getApplicationId() : null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        System.out.println("**********************************************");

        return roomsApplications(applicationIds);
    }

    private List<RoomDTO> roomsApplications(List<Integer> applicationIds) {
        // Lista para almacenar las salas que coinciden con todos las apps
        List<Room> matchedRooms = new ArrayList<>();

        // Conjunto para almacenar las salas que coinciden con cada app
        Set<Room> tempRooms = new HashSet<>();

        // Iterar sobre cada ID de app
        for (Integer applicationId : applicationIds) {
            Application application = applicationDAO.findById(applicationId);
            if (application != null) {
                // Obtener las salas que contienen esta app
                List<Room> rooms = roomDAO.findRoomsBySoftwareContaining(application);
                tempRooms.addAll(rooms);
                for (Room room : rooms) {
                    System.out.println("ID del salón: " + room.getRoomId());
                }

            }
        }


        Map<Integer, Integer> idCountMap = new HashMap<>();
        Set<Integer> addedRoomIds = new HashSet<>();
        // Contar la frecuencia de cada ID en tempRooms
        for (Room room : tempRooms) {
            int roomId = room.getRoomId();
            System.out.println(room.getRoomId());
            idCountMap.put(roomId, idCountMap.getOrDefault(roomId, 0) + 1);
        }
        System.out.println("\n -----------------------");
        // Agregar solo los salones cuyo ID aparece N veces, donde N es la cantidad de appsIds
        for (Room room : tempRooms) {
            int roomId = room.getRoomId();
            if (idCountMap.get(roomId) == applicationIds.size() && !addedRoomIds.contains(roomId)) {
                System.out.println(room.getRoomId());
                addedRoomIds.add(roomId);
                matchedRooms.add(room);
            }
        }
        return roomDTOMapper.toDTOs(matchedRooms);
    }



}



