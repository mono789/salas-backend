package co.edu.udea.salasinfo.controller.v1;


import co.edu.udea.salasinfo.configuration.advisor.responses.ExceptionResponse;
import co.edu.udea.salasinfo.configuration.advisor.responses.ValidationExceptionResponse;
import co.edu.udea.salasinfo.dto.request.auth.RegisterRequest;
import co.edu.udea.salasinfo.dto.response.auth.RegisterResponse;
import co.edu.udea.salasinfo.service.AuthenticationService;
import co.edu.udea.salasinfo.dto.request.auth.AuthenticationRequest;
import co.edu.udea.salasinfo.dto.response.auth.AuthenticationResponse;
import co.edu.udea.salasinfo.utils.RestConstants;
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
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Operation(summary = RestConstants.SWAGGER_LOGIN_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = RestConstants.CODE_ACCEPTED,
                    description = RestConstants.SWAGGER_LOGIN_SUCCESSFUL,
                    content = @Content(schema = @Schema(implementation = AuthenticationResponse.class))),
            @ApiResponse(responseCode = RestConstants.CODE_BAD_REQUEST,
                    description = RestConstants.SWAGGER_VALIDATIONS_DONT_PASS,
                    content = @Content(schema = @Schema(implementation = ValidationExceptionResponse.class))),
    })
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest request) {
        return ResponseEntity.accepted().body(authenticationService.authenticate(request));
    }

    @Operation(summary = RestConstants.SWAGGER_REGISTER_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = RestConstants.CODE_CREATED,
                    description = RestConstants.SWAGGER_REGISTER_SUCCESSFUL,
                    content = @Content(schema = @Schema(implementation = RegisterResponse.class))),
            @ApiResponse(responseCode = RestConstants.CODE_BAD_REQUEST,
                    description = RestConstants.SWAGGER_VALIDATIONS_DONT_PASS,
                    content = @Content(schema = @Schema(implementation = ValidationExceptionResponse.class))),
            @ApiResponse(responseCode = RestConstants.CODE_CONFLICT,
                    description = RestConstants.SWAGGER_USER_ALREADY_REGISTERED,
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.register(request));
    }
}