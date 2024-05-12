package co.edu.udea.SalasInfo.Service;

import co.edu.udea.SalasInfo.DAO.ImplementRepository;
import co.edu.udea.SalasInfo.DAO.RoomRepository;
import co.edu.udea.SalasInfo.Model.Implement;
import co.edu.udea.SalasInfo.Model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ImplementService {
    private final ImplementRepository implementRepository;
    private final RoomRepository roomRepository;



    @Autowired
    public ImplementService(RoomRepository roomRepository, ImplementRepository implementRepository) {
        this.implementRepository = implementRepository;
        this.roomRepository = roomRepository;
    }


    //retorna una lista de salones, recibe una lista(string) de impementos(columna implementName)
    //recibo una lista de string(implementos) y capturo sus id-->retorno una lista de id
    public List<Room> implementMatch(List<String> implementNames) {
        List<Implement> allImplements = implementRepository.findAll();

        // Mapear los nombres de implementos a sus identificadores
        List<Integer> implementIds = implementNames.stream()
                .map(name -> {
                    Implement implement = allImplements.stream()
                            .filter(impl -> impl.getImplementName().equals(name))
                            .findFirst()
                            .orElse(null);


                    return implement != null ? implement.getImplementId() : null;
                })
                .filter(id -> id != null)
                .collect(Collectors.toList());

        System.out.println("**********************************************");
        List<Room> matc = roomsImplements(implementIds);

        return matc;
    }

    public List<Room> roomsImplements(List<Integer> implementIds) {
        // Lista para almacenar las salas que coinciden con todos los implementos
        List<Room> matchedRooms = new ArrayList<>();

        // Conjunto para almacenar las salas que coinciden con cada implemento
        Set<Room> tempRooms = new HashSet<>();

        // Iterar sobre cada ID de implemento
        for (Integer implementId : implementIds) {
            Implement implement = implementRepository.findById(implementId).orElse(null);
            if (implement != null) {
                // Obtener las salas que contienen este implemento
                List<Room> rooms = roomRepository.findRoomsByImplementListContaining(implement);
                tempRooms.addAll(rooms);

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
        // Agregar solo los salones cuyo ID aparece N veces, donde N es la cantidad de implementIds
        for (Room room : tempRooms) {
            int roomId = room.getRoomId();
            if (idCountMap.get(roomId) == implementIds.size() && !addedRoomIds.contains(roomId)) {
                System.out.println(room.getRoomId());
                addedRoomIds.add(roomId);
                matchedRooms.add(room);
            }
        }
        return matchedRooms;
    }



}



