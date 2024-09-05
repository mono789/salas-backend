package co.edu.udea.salasinfo.controller;


import co.edu.udea.salasinfo.dto.response.room.RoomResponse;
import co.edu.udea.salasinfo.service.ImplementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/implements")
@RequiredArgsConstructor
public class ImplementController {
    private final ImplementService implementService;

    @GetMapping("/rooms")
    public ResponseEntity<List<RoomResponse>> implementMatch(@RequestParam List<String> implementNames) {
        List<RoomResponse> matchedRooms = implementService.implementMatch(implementNames);
        return ResponseEntity.ok(matchedRooms);
    }

    // ToDo: find implement rooms

}
