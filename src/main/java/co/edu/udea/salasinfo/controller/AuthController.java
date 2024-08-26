package co.edu.udea.salasinfo.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor  //constructor automatico
public class AuthController {
    /*
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
    @PutMapping("/update-role/{role}")
    public ResponseEntity<User> updateState(@RequestBody User user, @PathVariable String role){
        return authService.updateRol(user,role);
    }
     */
}
