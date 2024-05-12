package co.edu.udea.SalasInfo.Controller;


import co.edu.udea.SalasInfo.Model.Room;
import co.edu.udea.SalasInfo.Service.ImplementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/implement")
public class ImplementController {
    private final ImplementService implementService;
    @Autowired
    public ImplementController(ImplementService implementService) {
        this.implementService = implementService;
    }
    @GetMapping("/rooms")
    public ResponseEntity<List<Room>> implementMatch(@RequestParam List<String> implementNames) {
        List<Room> matchedRooms=implementService.implementMatch(implementNames);
        return  ResponseEntity.ok(matchedRooms);
    }


}
