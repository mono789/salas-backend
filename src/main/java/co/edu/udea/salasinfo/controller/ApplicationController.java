package co.edu.udea.salasinfo.controller;

import co.edu.udea.salasinfo.dto.response.room.RoomResponse;
import co.edu.udea.salasinfo.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/software")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    @GetMapping("/rooms")
    public ResponseEntity<List<RoomResponse>> applicationMatch(@RequestParam List<String> applicationNames) {
        List<RoomResponse> matchedRooms= applicationService.applicationMatch(applicationNames);
        return  ResponseEntity.ok(matchedRooms);
    }

    // ToDo: Find implement rooms

}
