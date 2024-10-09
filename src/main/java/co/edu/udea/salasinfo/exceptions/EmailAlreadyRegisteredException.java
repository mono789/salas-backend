package co.edu.udea.salasinfo.exceptions;

import co.edu.udea.salasinfo.utils.Constants;

public class EmailAlreadyRegisteredException extends RuntimeException {
    public EmailAlreadyRegisteredException(String email) {
        super(String.format(Constants.EMAIL_ALREADY_REGISTERED_MESSAGE, email));
    }
}