package co.edu.udea.salasinfo.controller;

import co.edu.udea.salasinfo.dto.request.RoomRequest;
import co.edu.udea.salasinfo.dto.request.filter.RoomFilter;
import co.edu.udea.salasinfo.configuration.advisor.responses.ExceptionResponse;
import co.edu.udea.salasinfo.dto.response.room.RoomResponse;
import co.edu.udea.salasinfo.dto.response.room.RoomScheduleResponse;
import co.edu.udea.salasinfo.dto.response.room.SpecificRoomResponse;
import co.edu.udea.salasinfo.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @Operation(summary = "Find all rooms and, if you pass a filter parameter, filters it")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found rooms",

                    content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = RoomResponse.class))))
    })
    @GetMapping
    public ResponseEntity<List<RoomResponse>> findAll(RoomFilter filter) {
        return ResponseEntity.ok(roomService.findAll(filter));
    }

    @PostMapping
    public ResponseEntity<RoomResponse> save(@RequestBody RoomRequest room) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.createRoom(room));
    }

    @Operation(summary = "Find a specific room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found room",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SpecificRoomResponse.class))),
            @ApiResponse(responseCode = "404", description = "Room not found", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
    })
    @GetMapping("/{id}")
    public ResponseEntity<SpecificRoomResponse> findById(@PathVariable Long id) {
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

    @Operation(summary = "Find free room right now")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found free rooms",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RoomResponse.class))))
    })
    @GetMapping("/free")
    public ResponseEntity<List<RoomResponse>> findFree() {
        return ResponseEntity.ok(roomService.findFreeAt(LocalDateTime.now()));
    }

    @Operation(summary = "Find schedule free room at a specific time")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found free rooms",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RoomResponse.class))))
    })
    @GetMapping("/free/{date}")
    public ResponseEntity<List<RoomResponse>> findFree(@PathVariable LocalDateTime date) {
        return ResponseEntity.ok(roomService.findFreeAt(date));
    }

    @Operation(summary = "Find schedule of a specific room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found schedule list",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RoomScheduleResponse.class)))),
            @ApiResponse(responseCode = "404", description = "Room not found", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
    })
    @GetMapping("/{id}/schedule")
    public ResponseEntity<List<RoomScheduleResponse>> findSchedule(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.findRoomSchedule(id));
    }

}
