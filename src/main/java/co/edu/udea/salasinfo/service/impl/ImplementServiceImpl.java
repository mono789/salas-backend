package co.edu.udea.salasinfo.service.impl;

import co.edu.udea.salasinfo.dto.response.room.RoomResponse;
import co.edu.udea.salasinfo.mapper.response.RoomResponseMapper;
import co.edu.udea.salasinfo.persistence.ImplementDAO;
import co.edu.udea.salasinfo.model.Implement;
import co.edu.udea.salasinfo.model.Room;
import co.edu.udea.salasinfo.service.ImplementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ImplementServiceImpl implements ImplementService {
    private final ImplementDAO implementDAO;
    private final RoomResponseMapper roomResponseMapper;

    //retorna una lista de salones, recibe una lista(string) de impementos(columna implementName)
    //recibo una lista de string(implementos) y capturo sus id-->retorno una lista de id
    public List<RoomResponse> implementMatch(List<String> implementNames) {
        List<Implement> allImplements = implementDAO.findAll();

        // Mapear los nombres de implementos a sus identificadores
        List<Long> implementIds = implementNames.stream()
                .map(name -> {
                    Implement implement = allImplements.stream()
                            .filter(impl -> impl.getImplementName().equals(name))
                            .findFirst()
                            .orElse(null);
                    return implement != null ? implement.getImplementId() : null;
                })
                .filter(Objects::nonNull)
                .toList();

        return roomsImplements(implementIds);
    }

    private List<RoomResponse> roomsImplements(List<Long> implementIds) {
        // Lista para almacenar las salas que coinciden con todos los implementos
        List<Room> matchedRooms = new ArrayList<>();

        // Conjunto para almacenar las salas que coinciden con cada implemento
        Set<Room> tempRooms = new HashSet<>();

        // Iterar sobre cada ID de implemento
        for (Long implementId : implementIds) {
            Implement implement = implementDAO.findById(implementId);
            if (implement != null) {
                // Obtener las salas que contienen este implemento
                List<Room> rooms = implementDAO.findRoomsByImplementId(implementId);
                tempRooms.addAll(rooms);
            }
        }
        Map<Long, Integer> idCountMap = new HashMap<>();
        Set<Long> addedRoomIds = new HashSet<>();
        // Contar la frecuencia de cada ID en tempRooms
        tempRooms.forEach(room -> {
            Long roomId = room.getRoomId();
            idCountMap.put(roomId, idCountMap.getOrDefault(roomId, 0) + 1);

        });
        // Agregar solo los salones cuyo ID aparece N veces, donde N es la cantidad de implementIds
        tempRooms.forEach(room -> {
            Long roomId = room.getRoomId();
            if (idCountMap.get(roomId) == implementIds.size() && !addedRoomIds.contains(roomId)) {
                addedRoomIds.add(roomId);
                matchedRooms.add(room);
            }
        });
        return roomResponseMapper.toResponses(matchedRooms);
    }


}



