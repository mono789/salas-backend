package co.edu.udea.salasinfo.service;

import co.edu.udea.salasinfo.dto.request.RoomRequest;
import co.edu.udea.salasinfo.dto.response.room.RoomResponse;

import java.time.LocalDateTime;
import java.util.List;

/**
 * It's the rooms data accessor, which saves and retrieves rooms
 */
public interface RoomService {
    List<RoomResponse> findAll();
    RoomResponse findById(Long id) ;
    RoomResponse createRoom(RoomRequest room) ;
    RoomResponse updateRoom(Long id, RoomRequest room) ;
    RoomResponse deleteRoom(Long id) ;
    List<RoomResponse> findFreeAt(LocalDateTime date);
}
