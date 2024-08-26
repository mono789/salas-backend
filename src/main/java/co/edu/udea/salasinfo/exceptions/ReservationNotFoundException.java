package co.edu.udea.salasinfo.exceptions;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException(Integer id) {
        super("Reservation of room with id " + id + " not found");
    }
}
