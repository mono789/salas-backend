package co.edu.udea.salasinfo.controller;


import co.edu.udea.salasinfo.dto.RoomDTO;
import co.edu.udea.salasinfo.service.ImplementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/implement")
@RequiredArgsConstructor
public class ImplementController {
    private final ImplementService implementService;

    @GetMapping("/rooms")
    public ResponseEntity<List<RoomDTO>> implementMatch(@RequestParam List<String> implementNames) {
        List<RoomDTO> matchedRooms=implementService.implementMatch(implementNames);
        return  ResponseEntity.ok(matchedRooms);
    }


}
