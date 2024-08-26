package co.edu.udea.salasinfo.controller;

import co.edu.udea.salasinfo.dto.RoomDTO;
import co.edu.udea.salasinfo.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping()
    public ResponseEntity<List<RoomDTO>> findAll() {
        return ResponseEntity.ok(roomService.findAll());
    }

    @PostMapping()
    public ResponseEntity<RoomDTO> save(@RequestBody RoomDTO room) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.createRoom(room));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(roomService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RoomDTO> remove(@PathVariable Integer id) {
        return ResponseEntity.ok(roomService.deleteRoom(id));
    }

    @PutMapping()
    public ResponseEntity<RoomDTO> update(@RequestBody RoomDTO room) {
        return ResponseEntity.ok(roomService.updateRoom(room));
    }

    @GetMapping("/find-by-application/{applicationId}")
    public ResponseEntity<List<RoomDTO>> findByApplication(@PathVariable Integer applicationId) {
        return ResponseEntity.ok(roomService.findRoomsBySoftwareId(applicationId));
    }

    @GetMapping("/find-by-implement/{implementId}")
    public ResponseEntity<List<RoomDTO>> findByImplement(@PathVariable Integer implementId) {
        return ResponseEntity.ok(roomService.findRoomsByImplementId(implementId));
    }
}
