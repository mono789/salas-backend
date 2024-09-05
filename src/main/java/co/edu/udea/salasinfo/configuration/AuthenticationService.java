package co.edu.udea.salasinfo.configuration;

import co.edu.udea.salasinfo.configuration.security.jwt.JwtService;
import co.edu.udea.salasinfo.dto.auth.AuthenticationRequest;
import co.edu.udea.salasinfo.dto.auth.AuthenticationResponse;
import co.edu.udea.salasinfo.exceptions.EntityNotFoundException;
import co.edu.udea.salasinfo.model.User;
import co.edu.udea.salasinfo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(request.getEmail()));

        Map<String, String> claims = new HashMap<>();
        claims.put("role", user.getAuthorities().stream().toList().get(0).getAuthority());
        String token = jwtService.generateToken(claims, user);

        return AuthenticationResponse.builder().token(token).build();
    }
}
