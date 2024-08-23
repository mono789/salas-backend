package co.edu.udea.SalasInfo.controller;

import co.edu.udea.SalasInfo.dto.ReservationDTO;
import co.edu.udea.SalasInfo.service.ReservationService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Builder
@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/find-all")
    public ResponseEntity<List<ReservationDTO>> findAll() {
        return ResponseEntity.ok(reservationService.findAll());
    }

    @GetMapping("/find-refused")
    public ResponseEntity<List<ReservationDTO>> findRefused(){
        return ResponseEntity.ok(reservationService.findStated(1));
    }

    @GetMapping("/find-accept")
    public ResponseEntity<List<ReservationDTO>> findAccept(){
        return ResponseEntity.ok(reservationService.findStated(2));
    }

    @GetMapping("/find-revision")
    public ResponseEntity<List<ReservationDTO>> findRevision(){
        return ResponseEntity.ok(reservationService.findStated(3));
    }

    @PutMapping("/accept")
    public ResponseEntity<ReservationDTO> acceptReservation(@RequestBody ReservationDTO reservation) {
        return ResponseEntity.ok(reservationService.updateState(reservation,2));
    }
    @PutMapping("/reject")
    public ResponseEntity<ReservationDTO> rejectReservation(@RequestBody ReservationDTO reservation) {
        return ResponseEntity.ok(reservationService.updateState(reservation,1));
    }

    @GetMapping("/free-all/{hora}")
    public ResponseEntity<List<ReservationDTO>> freeAll(@PathVariable String hora){
        return ResponseEntity.ok(reservationService.freeAll(hora));
    }

    @PostMapping("/save")
    public ResponseEntity<ReservationDTO> save(@RequestBody ReservationDTO reservation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.save(reservation));
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<ReservationDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(reservationService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ReservationDTO> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(reservationService.delete(id));
    }

    @PutMapping("/update")
    public ResponseEntity<ReservationDTO> update(@RequestBody ReservationDTO reservation) {
        return ResponseEntity.ok(reservationService.update(reservation));
    }

    @PutMapping("/update-state/{state}")
    public ResponseEntity<ReservationDTO> updateState(@RequestBody ReservationDTO reservation,@PathVariable Integer state){
        return ResponseEntity.ok(reservationService.updateState(reservation,state));
    }

    @GetMapping("/find-by-room/{roomId}")
    public ResponseEntity<List<ReservationDTO>> findByRoomId(@PathVariable Integer roomId){
        return ResponseEntity.ok(reservationService.findReservationByRoomId(roomId));
    }



}