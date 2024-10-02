package co.edu.udea.salasinfo.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String name, Object id) {
        super(name + " with " + id + " not found");
    }

    public EntityNotFoundException(String message){
        super(message);
    }
}
