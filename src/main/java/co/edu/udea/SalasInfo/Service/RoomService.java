package co.edu.udea.SalasInfo.Service;

import co.edu.udea.SalasInfo.Model.Room;
import co.edu.udea.SalasInfo.DAO.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    /**
     * Retrieves a List of Rooms from the Database
     *
     * @return A list of the retrieved Rooms.
     */
    public ResponseEntity<List<Room>> findAll() {
        List<Room> rooms = new ArrayList<>(roomRepository.findAll());
        return ResponseEntity.ok(rooms);
    }

    /**
     * Seeks for a Room in the database given a specific id.
     *
     * @param id The id of the Wanted Room.
     * @return A Response Entity with the found room as body and status code 200
     * Or a Response Entity without body and status code 404.
     */
    public ResponseEntity<Room> findById(Integer id) {
        Optional<Room> optRoom = roomRepository.findById(id);
        return optRoom.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    /**
     * Receives a room, creates the room's id using the building, number and sub room, then save it in the database.
     *
     * @param room A room object with the needed attributes.
     * @return A response entity with a 200 as status code and the saved room as body if there's a success and a 400 if something is wrong.
     */
    public ResponseEntity<Room> createRoom(Room room) {
        String stringId = room.getBuilding() +
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

    /**
     * Updates the room of the given room's id with using the changes of the given room object.
     *
     * @param room The room that contain the changes.
     * @return A response entity with 200 as status code and the updated room as body
     * or a response entity with 404 as status code.
     */
    public ResponseEntity<Room> updateRoom(Room room) {
        Integer id = room.getRoomId();
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isEmpty()) return ResponseEntity.notFound().build();
        Room roomToUpdate = optionalRoom.get();
        if (room.getRoomName() != null) roomToUpdate.setRoomName(room.getRoomName());
        if (room.getComputerAmount() != null) roomToUpdate.setComputerAmount(room.getComputerAmount());
        roomRepository.save(roomToUpdate);
        return ResponseEntity.ok(roomToUpdate);
    }

    /**
     * Deletes the room of the given id if it exists.
     *
     * @param id The id of the room that is going to be deleted.
     * @return The Deleted room.
     */
    public ResponseEntity<Room> deleteRoom(int id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isEmpty()) return ResponseEntity.notFound().build();
        roomRepository.deleteById(id);
        return ResponseEntity.ok(optionalRoom.get());
    }
}
