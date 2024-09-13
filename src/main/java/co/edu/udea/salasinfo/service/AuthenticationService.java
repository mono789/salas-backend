package co.edu.udea.salasinfo.service;

import co.edu.udea.salasinfo.dto.request.auth.AuthenticationRequest;
import co.edu.udea.salasinfo.dto.response.auth.AuthenticationResponse;
import co.edu.udea.salasinfo.dto.request.auth.RegisterRequest;
import co.edu.udea.salasinfo.dto.response.auth.RegisterResponse;

public interface AuthenticationService {

    AuthenticationResponse authenticate(AuthenticationRequest request);

    RegisterResponse register(RegisterRequest request);
}
