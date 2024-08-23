package co.edu.udea.SalasInfo.exceptions;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException(Integer id) {
        super("Reservation of room with id " + id + " not found");
    }
}
