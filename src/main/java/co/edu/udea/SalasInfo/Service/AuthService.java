package co.edu.udea.SalasInfo.Service;

import co.edu.udea.SalasInfo.Auth.AuthResponse;
import co.edu.udea.SalasInfo.Auth.LoginRequest;
import co.edu.udea.SalasInfo.Auth.RegisterRequest;
import co.edu.udea.SalasInfo.Auth.Role;
import co.edu.udea.SalasInfo.DAO.UserRepository;
import co.edu.udea.SalasInfo.Jwt.JwtService;
import co.edu.udea.SalasInfo.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    //Primero debo crear el usuario al cual voy a acceder
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        //se crea el objeto usuario
        User user= User.builder()
                .customerId(Long.valueOf(request.getCustomerId()))
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .role(Role.USER)    //cuando se cree por primera vez sera del tipo USER
                .build();
        //System.out.println(passwordEncoder.encode(request.getPassword()));
        //Se gusrda en DB usando el repositorio
        userRepository.save(user);  //cuando cargo todo lo guardo en la tabla
        //se obtiene el token atravez del servicio de jwt y se retorna al cntroller
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
