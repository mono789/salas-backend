package co.edu.udea.salasinfo.service;

import co.edu.udea.salasinfo.dto.UserDTO;
import co.edu.udea.salasinfo.dto.auth.AuthResponse;
import co.edu.udea.salasinfo.dto.auth.LoginRequest;
import co.edu.udea.salasinfo.dto.auth.RegisterRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {
    AuthResponse login(LoginRequest request);
    AuthResponse register(RegisterRequest request);
    UserDTO updateRol(@RequestBody UserDTO user, @PathVariable String rol );
}
