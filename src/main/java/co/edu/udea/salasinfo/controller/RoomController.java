package co.edu.udea.salasinfo.controller;

import co.edu.udea.salasinfo.dto.request.RoomRequest;
import co.edu.udea.salasinfo.dto.response.room.RoomResponse;
import co.edu.udea.salasinfo.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping()
    public ResponseEntity<List<RoomResponse>> findAll() {
        return ResponseEntity.ok(roomService.findAll());
    }

    @PostMapping()
    public ResponseEntity<RoomResponse> save(@RequestBody RoomRequest room) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.createRoom(room));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RoomResponse> remove(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.deleteRoom(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponse> update(@PathVariable Long id, @RequestBody RoomRequest room) {
        return ResponseEntity.ok(roomService.updateRoom(id, room));
    }

    @GetMapping("/free")
    public ResponseEntity<List<RoomResponse>> findFree() {
        return ResponseEntity.ok(roomService.findFreeAt(LocalDateTime.now()));
    }

    @GetMapping("/free/{date}")
    public ResponseEntity<List<RoomResponse>> findFree(@PathVariable LocalDateTime date) {
        return ResponseEntity.ok(roomService.findFreeAt(date));
    }

    // ToDo: Find reservations of a room
}
