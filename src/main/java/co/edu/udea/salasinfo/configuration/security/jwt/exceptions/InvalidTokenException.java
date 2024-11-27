package co.edu.udea.salasinfo.configuration.security.jwt.exceptions;

import co.edu.udea.salasinfo.utils.Constants;
import org.springframework.security.core.AuthenticationException;

public class InvalidTokenException extends AuthenticationException {
    public InvalidTokenException() {
        super(Constants.INVALID_TOKEN_MESSAGE);
    }
}
