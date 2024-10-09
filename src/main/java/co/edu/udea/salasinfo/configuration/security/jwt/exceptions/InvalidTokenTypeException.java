package co.edu.udea.salasinfo.configuration.security.jwt.exceptions;

import co.edu.udea.salasinfo.utils.Constants;

public class InvalidTokenTypeException extends RuntimeException {
    public InvalidTokenTypeException(){
        super(Constants.INVALID_TOKEN_TYPE_MESSAGE);
    }
}
