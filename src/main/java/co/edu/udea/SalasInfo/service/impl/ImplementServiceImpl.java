package co.edu.udea.SalasInfo.service.impl;

import co.edu.udea.SalasInfo.dto.RoomDTO;
import co.edu.udea.SalasInfo.mapper.RoomDTOMapper;
import co.edu.udea.SalasInfo.persistence.ImplementDAO;
import co.edu.udea.SalasInfo.persistence.RoomDAO;
import co.edu.udea.SalasInfo.model.Implement;
import co.edu.udea.SalasInfo.model.Room;
import co.edu.udea.SalasInfo.service.ImplementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImplementServiceImpl implements ImplementService {
    private final ImplementDAO implementDAO;
    private final RoomDAO roomDAO;
    private final RoomDTOMapper roomDTOMapper;

    //retorna una lista de salones, recibe una lista(string) de impementos(columna implementName)
    //recibo una lista de string(implementos) y capturo sus id-->retorno una lista de id
    public List<RoomDTO> implementMatch(List<String> implementNames) {
        List<Implement> allImplements = implementDAO.findAll();

        // Mapear los nombres de implementos a sus identificadores
        List<Integer> implementIds = implementNames.stream()
                .map(name -> {
                    Implement implement = allImplements.stream()
                            .filter(impl -> impl.getImplementName().equals(name))
                            .findFirst()
                            .orElse(null);


                    return implement != null ? implement.getImplementId() : null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        System.out.println("**********************************************");

        return roomsImplements(implementIds);
    }

    private List<RoomDTO> roomsImplements(List<Integer> implementIds) {
        // Lista para almacenar las salas que coinciden con todos los implementos
        List<Room> matchedRooms = new ArrayList<>();

        // Conjunto para almacenar las salas que coinciden con cada implemento
        Set<Room> tempRooms = new HashSet<>();

        // Iterar sobre cada ID de implemento
        for (Integer implementId : implementIds) {
            Implement implement = implementDAO.findById(implementId);
            if (implement != null) {
                // Obtener las salas que contienen este implemento
                List<Room> rooms = roomDAO.findRoomsByImplementListContaining(implement);
                tempRooms.addAll(rooms);

            }
        }
        Map<Integer, Integer> idCountMap = new HashMap<>();
        Set<Integer> addedRoomIds = new HashSet<>();
        // Contar la frecuencia de cada ID en tempRooms
        for (Room room : tempRooms) {
            int roomId = room.getRoomId();
            idCountMap.put(roomId, idCountMap.getOrDefault(roomId, 0) + 1);
        }
        // Agregar solo los salones cuyo ID aparece N veces, donde N es la cantidad de implementIds
        for (Room room : tempRooms) {
            int roomId = room.getRoomId();
            if (idCountMap.get(roomId) == implementIds.size() && !addedRoomIds.contains(roomId)) {
                addedRoomIds.add(roomId);
                matchedRooms.add(room);
            }
        }
        return roomDTOMapper.toDTOs(matchedRooms);
    }



}



