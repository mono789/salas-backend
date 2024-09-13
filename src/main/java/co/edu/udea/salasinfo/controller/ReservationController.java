package co.edu.udea.salasinfo.controller;

import co.edu.udea.salasinfo.dto.request.ClassReservationRequest;
import co.edu.udea.salasinfo.dto.request.ReservationRequest;
import co.edu.udea.salasinfo.configuration.advisor.responses.ExceptionResponse;
import co.edu.udea.salasinfo.dto.response.reservation.ReservationResponse;
import co.edu.udea.salasinfo.configuration.advisor.responses.ValidationExceptionResponse;
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

    @Operation(summary = "find all reservations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List with all reservations", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ReservationResponse.class)))),
    })
    @GetMapping()
    public ResponseEntity<List<ReservationResponse>> findAll() {
        return ResponseEntity.ok(reservationService.findAll());
    }

    @Operation(summary = "find rejected reservations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List with just refused reservations", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ReservationResponse.class)))),
    })
    @GetMapping("/rejected")
    public ResponseEntity<List<ReservationResponse>> findRefused() {
        return ResponseEntity.ok(reservationService.findStated(RStatus.REJECTED));
    }

    @Operation(summary = "find accepted reservations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List with just accepted reservations", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ReservationResponse.class)))),
    })
    @GetMapping("/accepted")
    public ResponseEntity<List<ReservationResponse>> findAccept() {
        return ResponseEntity.ok(reservationService.findStated(RStatus.ACCEPTED));
    }

    @Operation(summary = "find pending reservations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List with just pending reservations", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ReservationResponse.class)))),
    })
    @GetMapping("/pending")
    public ResponseEntity<List<ReservationResponse>> findRevision() {
        return ResponseEntity.ok(reservationService.findStated(RStatus.PENDING));
    }

    @Operation(summary = "Changes the state of a function to accepted")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation has been accepted", content = @Content(schema = @Schema(implementation = ReservationResponse.class))),
            @ApiResponse(responseCode = "404", description = "Reservation not found", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PutMapping("/{id}/accept")
    public ResponseEntity<ReservationResponse> acceptReservation(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.updateState(id, RStatus.ACCEPTED));
    }

    @Operation(summary = "Changes the state of a function to rejected")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation has been rejected", content = @Content(schema = @Schema(implementation = ReservationResponse.class))),
            @ApiResponse(responseCode = "404", description = "Reservation not found", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PutMapping("/{id}/reject")
    public ResponseEntity<ReservationResponse> rejectReservation(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.updateState(id, RStatus.REJECTED));
    }
}