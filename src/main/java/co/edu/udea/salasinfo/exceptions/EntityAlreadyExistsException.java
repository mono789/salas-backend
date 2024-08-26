package co.edu.udea.salasinfo.exceptions;

public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(String message) {
        super(message + " already exists");
    }
}
