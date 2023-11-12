package co.edu.udea.SalasInfo.Controller;

import co.edu.udea.SalasInfo.Model.Application;
import co.edu.udea.SalasInfo.Model.Room;
import co.edu.udea.SalasInfo.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoomController {

    @Autowired
    RoomService roomService;

    @GetMapping("/room")
    public ResponseEntity<List<Room>> findAll() {
        return roomService.findAll();
    }

    @PostMapping("/room")
    public ResponseEntity<Room> save(@RequestBody Room room) {
        return roomService.createRoom(room);
    }

    @GetMapping("/room/{id}")
    public ResponseEntity<Room> findById(@PathVariable Integer id) {
        return roomService.findById(id);
    }

    @DeleteMapping("/room/{id}")
    public ResponseEntity<Room> remove(@PathVariable Integer id) {
        return roomService.deleteRoom(id);
    }

    @PutMapping("/room/{id}")
    public ResponseEntity<Room> update(@PathVariable Integer id,@RequestBody Room room) {
        return roomService.updateRoom(id, room);
    }

    @GetMapping("/room/{id}/software")
    public ResponseEntity<List<Application>> findRoomSoftwareById(@PathVariable Integer id) {
        return roomService.findRoomSoftware(id);
    }
}
