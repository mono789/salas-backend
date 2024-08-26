package co.edu.udea.salasinfo.service;

import co.edu.udea.salasinfo.dto.RoomDTO;

import java.util.List;

/**
 * It's the rooms data accessor, which saves and retrieves rooms
 */
public interface RoomService {
    List<RoomDTO> findAll();
    RoomDTO findById(Integer id) ;
    RoomDTO createRoom(RoomDTO room) ;
    RoomDTO updateRoom(RoomDTO room) ;
    RoomDTO deleteRoom(int id) ;
    List<RoomDTO> findRoomsBySoftwareId(Integer applicationId);
    List<RoomDTO> findRoomsByImplementId(Integer implementId);
}
