package co.edu.udea.salasinfo.controller;

import co.edu.udea.salasinfo.dto.request.ReservationRequest;
import co.edu.udea.salasinfo.dto.response.ReservationResponse;
import co.edu.udea.salasinfo.service.ReservationService;
import co.edu.udea.salasinfo.utils.enums.RStatus;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Builder
@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping()
    public ResponseEntity<List<ReservationResponse>> findAll() {
        return ResponseEntity.ok(reservationService.findAll());
    }

    @GetMapping("/refused")
    public ResponseEntity<List<ReservationResponse>> findRefused(){
        return ResponseEntity.ok(reservationService.findStated(RStatus.REFUSED));
    }

    @GetMapping("/accepted")
    public ResponseEntity<List<ReservationResponse>> findAccept(){
        return ResponseEntity.ok(reservationService.findStated(RStatus.ACCEPTED));
    }

    @GetMapping("/revision")
    public ResponseEntity<List<ReservationResponse>> findRevision(){
        return ResponseEntity.ok(reservationService.findStated(RStatus.IN_REVISION));
    }

    @PutMapping("/{id}/accept")
    public ResponseEntity<ReservationResponse> acceptReservation(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.updateState(id,RStatus.ACCEPTED));
    }
    @PutMapping("/{id}/reject")
    public ResponseEntity<ReservationResponse> rejectReservation(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.updateState(id, RStatus.REFUSED));
    }

    @PostMapping()
    public ResponseEntity<ReservationResponse> save(@RequestBody ReservationRequest reservation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.save(reservation));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReservationResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponse> update(@PathVariable Long id, @Valid @RequestBody ReservationRequest reservation) {
        return ResponseEntity.ok(reservationService.update(id, reservation));
    }
}