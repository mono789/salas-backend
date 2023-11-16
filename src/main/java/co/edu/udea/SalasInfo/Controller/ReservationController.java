package co.edu.udea.SalasInfo.Controller;

import co.edu.udea.SalasInfo.Model.Reservation;
import co.edu.udea.SalasInfo.Service.ReservationService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Builder
@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @GetMapping("/find-all")
    public List<Reservation> findAll() {
        return reservationService.findAll();
    }

    @GetMapping("/free-all/{hora}")
    public List<Reservation> freeAll(@PathVariable String hora){
        return reservationService.freeAll(hora);
    }

    @PostMapping("/save")
    public ResponseEntity<Reservation> save(@RequestBody Reservation reservation) {
        return reservationService.save(reservation);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<Reservation> findById(@PathVariable Integer id) {
        return reservationService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Reservation> delete(@PathVariable Integer id) {
        return reservationService.delete(id);
    }

    @PutMapping("/update")
    public ResponseEntity<Reservation> update(@RequestBody Reservation reservation) {
        return reservationService.update(reservation);
    }


}