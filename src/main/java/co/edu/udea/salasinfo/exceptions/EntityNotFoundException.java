package co.edu.udea.salasinfo.exceptions;

import co.edu.udea.salasinfo.utils.Constants;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String name, String id) {
        super(String.format(Constants.ENTITY_NOT_FOUND_MESSAGE, name, id));
    }

    public EntityNotFoundException(String message){
        super(message);
    }
}
