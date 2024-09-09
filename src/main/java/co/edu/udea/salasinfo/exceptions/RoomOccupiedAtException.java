package co.edu.udea.salasinfo.exceptions;

import co.edu.udea.salasinfo.utils.Constants;

import java.time.LocalDateTime;

public class RoomOccupiedAtException extends RuntimeException {
    public RoomOccupiedAtException(String room, LocalDateTime date) {
        super(String.format(Constants.ROOM_OCCUPIED_AT_MESSAGE, room, date));
    }
}
