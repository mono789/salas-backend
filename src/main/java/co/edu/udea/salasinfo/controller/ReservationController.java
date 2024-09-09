package co.edu.udea.salasinfo.controller;

import co.edu.udea.salasinfo.dto.request.ClassReservationRequest;
import co.edu.udea.salasinfo.dto.request.ReservationRequest;
import co.edu.udea.salasinfo.dto.response.ExceptionResponse;
import co.edu.udea.salasinfo.dto.response.ReservationResponse;
import co.edu.udea.salasinfo.dto.response.ValidationExceptionResponse;
import co.edu.udea.salasinfo.service.ReservationService;
import co.edu.udea.salasinfo.utils.enums.RStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "creates a new reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "reservation has been saved", content = @Content(schema = @Schema(implementation = ReservationResponse.class))),
            @ApiResponse(responseCode = "400", description = "any of the validations is not fulfilled", content = @Content(schema = @Schema(implementation = ValidationExceptionResponse.class))),
            @ApiResponse(responseCode = "409", description = "A reservation at that time already exists", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
    })
    @PostMapping()
    public ResponseEntity<ReservationResponse> save(@RequestBody @Valid ReservationRequest reservation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.save(reservation));
    }

    @GetMapping()
    public ResponseEntity<List<ReservationResponse>> findAll() {
        return ResponseEntity.ok(reservationService.findAll());
    }

    @Operation(summary = "creates a bunch of reservations for all the semester")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "reservations have been saved", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ReservationResponse.class)))),
            @ApiResponse(responseCode = "400", description = "any of the validations is not fulfilled", content = @Content(schema = @Schema(implementation = ValidationExceptionResponse.class))),
            @ApiResponse(responseCode = "409", description = "A reservation at that time, in that room already exists", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
    })
    @PostMapping("/class")
    public ResponseEntity<List<ReservationResponse>> createClass(@RequestBody @Valid ClassReservationRequest reservation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                reservationService.saveClass(reservation)
        );
    }

    @GetMapping("/refused")
    public ResponseEntity<List<ReservationResponse>> findRefused() {
        return ResponseEntity.ok(reservationService.findStated(RStatus.REFUSED));
    }

    @GetMapping("/accepted")
    public ResponseEntity<List<ReservationResponse>> findAccept() {
        return ResponseEntity.ok(reservationService.findStated(RStatus.ACCEPTED));
    }

    @GetMapping("/revision")
    public ResponseEntity<List<ReservationResponse>> findRevision() {
        return ResponseEntity.ok(reservationService.findStated(RStatus.IN_REVISION));
    }

    @PutMapping("/{id}/accept")
    public ResponseEntity<ReservationResponse> acceptReservation(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.updateState(id, RStatus.ACCEPTED));
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<ReservationResponse> rejectReservation(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.updateState(id, RStatus.REFUSED));
    }
}