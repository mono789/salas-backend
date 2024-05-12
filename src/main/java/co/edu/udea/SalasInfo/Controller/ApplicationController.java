package co.edu.udea.SalasInfo.Controller;

import co.edu.udea.SalasInfo.Model.Room;
import co.edu.udea.SalasInfo.Service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/application")
public class ApplicationController {
    private final ApplicationService applicationService;
    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }
    @GetMapping("/rooms")
    public ResponseEntity<List<Room>> applicationMatch(@RequestParam List<String> applicationNames) {
        List<Room> matchedRooms= applicationService.applicationMatch(applicationNames);
        return  ResponseEntity.ok(matchedRooms);
    }


}
