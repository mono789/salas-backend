package co.edu.udea.salasinfo.exceptions;

import co.edu.udea.salasinfo.utils.Constants;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException(Long id) {
        super(String.format(Constants.RESERVATION_NOT_FOUND_MESSAGE, id));
    }
}
