package co.edu.udea.salasinfo.controller;


import co.edu.udea.salasinfo.configuration.advisor.responses.ExceptionResponse;
import co.edu.udea.salasinfo.configuration.advisor.responses.ValidationExceptionResponse;
import co.edu.udea.salasinfo.dto.request.auth.RegisterRequest;
import co.edu.udea.salasinfo.dto.response.auth.RegisterResponse;
import co.edu.udea.salasinfo.service.AuthenticationService;
import co.edu.udea.salasinfo.dto.request.auth.AuthenticationRequest;
import co.edu.udea.salasinfo.dto.response.auth.AuthenticationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Authenticate a user with their email and password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "User has been authenticated", content = @Content(schema = @Schema(implementation = AuthenticationResponse.class))),
            @ApiResponse(responseCode = "400", description = "Any of the validations don't pass", content = @Content(schema = @Schema(implementation = ValidationExceptionResponse.class))),
    })
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest request) {
        return ResponseEntity.accepted().body(authenticationService.authenticate(request));
    }

    @Operation(summary = "Create a new user in the system with USER role by default")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User has been registered", content = @Content(schema = @Schema(implementation = RegisterResponse.class))),
            @ApiResponse(responseCode = "400", description = "Any of the validations don't pass", content = @Content(schema = @Schema(implementation = ValidationExceptionResponse.class))),
            @ApiResponse(responseCode = "409", description = "A user with that email is already registered", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.register(request));
    }
}
