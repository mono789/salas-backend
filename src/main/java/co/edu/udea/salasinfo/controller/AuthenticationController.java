package co.edu.udea.salasinfo.controller;


import co.edu.udea.salasinfo.configuration.AuthenticationService;
import co.edu.udea.salasinfo.dto.auth.AuthenticationRequest;
import co.edu.udea.salasinfo.dto.auth.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor  //constructor automatico
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {      //respuesta de tipo hhtp
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
