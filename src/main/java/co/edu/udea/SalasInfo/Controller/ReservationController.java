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

    @GetMapping("/find-refused")
    public List<Reservation> findRefused(){
        return reservationService.findStated(1);
    }

    @GetMapping("/find-accept")
    public List<Reservation> findAccept(){
        return reservationService.findStated(2);
    }

    @GetMapping("/find-revision")
    public List<Reservation> findRevision(){
        return reservationService.findStated(3);
    }

    @PutMapping("/accept")
    public ResponseEntity<Reservation> acceptReservation(@RequestBody Reservation reservation) {
        return reservationService.updateState(reservation,2);
    }
    @PutMapping("/reject")
    public ResponseEntity<Reservation> rejectReservation(@RequestBody Reservation reservation) {
        return reservationService.updateState(reservation,1);
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

    @PutMapping("/update-state")
    public ResponseEntity<Reservation> updateState(@RequestBody Reservation reservation,@PathVariable Integer state){
        return reservationService.updateState(reservation,state);
    }

    @GetMapping("/find-by-room/{roomId}")
    public ResponseEntity<List<Reservation>> findByRoomId(@PathVariable Integer roomId){
        return reservationService.findReservationByRoomId(roomId);
    }



}