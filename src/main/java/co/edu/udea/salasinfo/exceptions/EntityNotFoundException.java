package co.edu.udea.salasinfo.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String name, Integer id) {
        super(name + " with id " + id + " not found");
    }

    public EntityNotFoundException(String message){
        super(message);
    }
}
