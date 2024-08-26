package co.edu.udea.salasinfo.service.impl;

import co.edu.udea.salasinfo.dto.RoomDTO;
import co.edu.udea.salasinfo.mapper.RoomDTOMapper;
import co.edu.udea.salasinfo.persistence.RoomDAO;
import co.edu.udea.salasinfo.model.Application;
import co.edu.udea.salasinfo.model.Implement;
import co.edu.udea.salasinfo.model.Room;
import co.edu.udea.salasinfo.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;

/**
 * It's the rooms data accessor, which saves and retrieves rooms
 */
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomDAO roomDAO;
    private final RoomDTOMapper roomDTOMapper;

    /**
     * Retrieves a List of Rooms from the Database
     *
     * @return A list of the retrieved Rooms.
     */
    public List<RoomDTO> findAll() {
        List<Room> rooms = roomDAO.findAll();
        return roomDTOMapper.toDTOs(rooms);
    }

    /**
     * Seeks for a Room in the database given a specific id.
     *
     * @param id The id of the Wanted Room.
     * @return A Response Entity with the found room as body and status code 200
     * Or a Response Entity without body and status code 404.
     */
    public RoomDTO findById(Integer id) {
        Room optRoom = roomDAO.findById(id);
        return roomDTOMapper.toDTO(optRoom);
    }


    /**
     * Receives a room, creates the room's id using the building, number and sub room, then save it in the database.
     *
     * @param room A room object with the needed attributes.
     * @return A response entity with a 200 as status code and the saved room as body if there's a success and a 400 if something is wrong.
     */
    public RoomDTO createRoom(RoomDTO room) {
        String stringId = room.getBuilding() +
                room.getRoomNum() +
                room.getSubRoom();
        Integer id = Integer.parseInt(stringId);
        try {
            Room optRoom = roomDAO.findById(id);
            throw new EntityExistsException("Room with id " + id + " already exists");
        }catch (EntityExistsException e) {
            room.setRoomId(id);
            return roomDTOMapper.toDTO(roomDAO.save(roomDTOMapper.toEntity(room)));
        }
    }

    /**
     * Updates the room of the given room's id with using the changes of the given room object.
     *
     * @param room The room that contain the changes.
     * @return A response entity with 200 as status code and the updated room as body
     * or a response entity with 404 as status code.
     */
    public RoomDTO updateRoom(RoomDTO room) {
        Integer id = room.getRoomId();
        Room foundRoom = roomDAO.findById(id);
        if (room.getRoomName() != null) foundRoom.setRoomName(room.getRoomName());
        if (room.getComputerAmount() != null) foundRoom.setComputerAmount(room.getComputerAmount());
        roomDAO.save(foundRoom);
        return roomDTOMapper.toDTO(foundRoom);
    }

    /**
     * Deletes the room of the given id if it exists.
     *
     * @param id The id of the room that is going to be deleted.
     * @return The Deleted room.
     */
    public RoomDTO deleteRoom(int id) {
        Room deletedRoom = roomDAO.findById(id);
        roomDAO.deleteById(id);
        return roomDTOMapper.toDTO(deletedRoom);
    }

    /**
     * A method that retrieves rooms which have a specific application
     * @param applicationId It's the application id with the method will search rooms.
     * @return A ResponseEntity with the found Rooms and status code 200.
     */
    public List<RoomDTO> findRoomsBySoftwareId(Integer applicationId){
        Application app = new Application();
        app.setApplicationId(applicationId);
        List<Room> foundRooms = roomDAO.findRoomsBySoftwareContaining(app);
        return roomDTOMapper.toDTOs(foundRooms);
    }

    /**
     * A method that retrieves rooms which have a specific implement
     * @param implementId It's the implement id the method will search rooms with.
     * @return A ResponseEntity with the found Rooms and status code 200.
     */
    public List<RoomDTO> findRoomsByImplementId(Integer implementId){
        Implement implement = new Implement();
        implement.setImplementId(implementId);
        List<Room> foundRooms = roomDAO.findRoomsByImplementListContaining(implement);
        return roomDTOMapper.toDTOs(foundRooms);
    }
}
