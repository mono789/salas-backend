package co.edu.udea.SalasInfo.controller;

import co.edu.udea.SalasInfo.dto.RoomDTO;
import co.edu.udea.SalasInfo.model.Room;
import co.edu.udea.SalasInfo.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    @GetMapping("/rooms")
    public ResponseEntity<List<RoomDTO>> applicationMatch(@RequestParam List<String> applicationNames) {
        List<RoomDTO> matchedRooms= applicationService.applicationMatch(applicationNames);
        return  ResponseEntity.ok(matchedRooms);
    }


}
