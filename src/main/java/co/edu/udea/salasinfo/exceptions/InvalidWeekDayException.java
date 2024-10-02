package co.edu.udea.salasinfo.exceptions;

import co.edu.udea.salasinfo.utils.Constants;

public class InvalidWeekDayException extends RuntimeException {
    public InvalidWeekDayException() {
        super(Constants.INVALID_DAY_OF_WEEK_MESSAGE);
    }
}
