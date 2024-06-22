package co.edu.udea.SalasInfo.Controller;


import co.edu.udea.SalasInfo.Auth.AuthResponse;
import co.edu.udea.SalasInfo.Auth.LoginRequest;
import co.edu.udea.SalasInfo.Auth.RegisterRequest;
import co.edu.udea.SalasInfo.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor  //constructor automatico
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){      //respuesta de tipo hhtp
        //busca el usuario autenticado
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public  ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        //se crea un nuevo regustro de usuario
        //y se retorna al cliente
        return ResponseEntity.ok(authService.register(request));

    }
}
