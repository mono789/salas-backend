package co.edu.udea.SalasInfo.Service;

import co.edu.udea.SalasInfo.Model.Room;
import co.edu.udea.SalasInfo.DAO.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public List<Room> findAll() {
        return new ArrayList<>(roomRepository.findAll());
    }

    public ResponseEntity<Room> findById(Integer id) {
        Optional<Room> optRoom = roomRepository.findById(id);
        return optRoom.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Room> createRoom(Room room) {
        String stringId = String.valueOf(room.getBuilding()) +
                room.getRoomNum() +
                room.getSubRoom();
        Integer id = Integer.parseInt(stringId);
        Optional<Room> optRoom = roomRepository.findById(id);
        if (optRoom.isPresent()) return null;
        room.setRoomId(id);
        try {
            return ResponseEntity.ok(roomRepository.save(room));
        } catch (EntityExistsException err) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<Room> updateRoom(int id, Room room) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isEmpty()) return ResponseEntity.notFound().build();
        Room roomToUpdate = optionalRoom.get();
        if (room.getRoomName() != null) roomToUpdate.setRoomName(room.getRoomName());
        if (room.getComputerAmount() != null) roomToUpdate.setComputerAmount(room.getComputerAmount());
        roomRepository.save(roomToUpdate);
        return ResponseEntity.ok(roomToUpdate);
    }

    public ResponseEntity<Room> deleteRoom(int id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isEmpty()) return ResponseEntity.notFound().build();
        roomRepository.deleteById(id);
        return ResponseEntity.ok(optionalRoom.get());
    }

}
