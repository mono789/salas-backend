package co.edu.udea.SalasInfo.Service;

import co.edu.udea.SalasInfo.DAO.ApplicationRepository;
import co.edu.udea.SalasInfo.DAO.RoomRepository;
import co.edu.udea.SalasInfo.Model.Application;
import co.edu.udea.SalasInfo.Model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final RoomRepository roomRepository;



    @Autowired
    public ApplicationService(RoomRepository roomRepository, ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
        this.roomRepository = roomRepository;
    }


    //retorna una lista de salones, recibe una lista(string) de application(columna applicationName)
    //recibo una lista de string(Applications) y capturo sus id-->retorno una lista de id
    public List<Room> applicationMatch(List<String> applicationNames) {
        List<Application> allApplications = applicationRepository.findAll();

        // Mapear los nombres de Application a sus identificadores
        List<Integer> applicationIds = applicationNames.stream()
                .map(name -> {
                    Application application = allApplications.stream()
                            .filter(app -> app.getApplicationName().equals(name))
                            .findFirst()
                            .orElse(null);

                    return application != null ? application.getApplicationId() : null;
                })
                .filter(id -> id != null)
                .collect(Collectors.toList());

        System.out.println("**********************************************");
        List<Room> matc = roomsApplications(applicationIds);

        return matc;
    }

    public List<Room> roomsApplications(List<Integer> applicationIds) {
        // Lista para almacenar las salas que coinciden con todos las apps
        List<Room> matchedRooms = new ArrayList<>();

        // Conjunto para almacenar las salas que coinciden con cada app
        Set<Room> tempRooms = new HashSet<>();

        // Iterar sobre cada ID de app
        for (Integer applicationId : applicationIds) {
            Application application = applicationRepository.findById(applicationId).orElse(null);
            if (application != null) {
                // Obtener las salas que contienen esta app
                List<Room> rooms = roomRepository.findRoomsBySoftwareContaining(application);
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
        return matchedRooms;
    }



}



