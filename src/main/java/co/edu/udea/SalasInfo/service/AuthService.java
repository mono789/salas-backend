package co.edu.udea.SalasInfo.service;

import co.edu.udea.SalasInfo.dto.UserDTO;
import co.edu.udea.SalasInfo.dto.auth.AuthResponse;
import co.edu.udea.SalasInfo.dto.auth.LoginRequest;
import co.edu.udea.SalasInfo.dto.auth.RegisterRequest;
import co.edu.udea.SalasInfo.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {
    AuthResponse login(LoginRequest request);
    AuthResponse register(RegisterRequest request);
    UserDTO updateRol(@RequestBody UserDTO user, @PathVariable String rol );
}
