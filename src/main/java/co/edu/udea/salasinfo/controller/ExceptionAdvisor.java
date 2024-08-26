package co.edu.udea.salasinfo.controller;

import co.edu.udea.salasinfo.dto.ExceptionDTO;
import co.edu.udea.salasinfo.exceptions.EntityAlreadyExistsException;
import co.edu.udea.salasinfo.exceptions.EntityNotFoundException;
import co.edu.udea.salasinfo.exceptions.ReservationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionAdvisor {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> handleRuntimeException(Exception e) {
        String message = e.getMessage() == null ? e.getClass().getSimpleName() : e.getMessage();
        ExceptionDTO exceptionResponse = ExceptionDTO.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .timestamp(LocalDateTime.now())
                .message(message).build();
        return ResponseEntity.status(exceptionResponse.getStatusCode()).body(exceptionResponse);
    }

    @ExceptionHandler({
            EntityNotFoundException.class,
            ReservationNotFoundException.class
    })
    public ResponseEntity<ExceptionDTO> handleNotFoundException(RuntimeException e) {
        String message = e.getMessage() == null ? e.getClass().getSimpleName() : e.getMessage();
        ExceptionDTO exceptionResponse = ExceptionDTO.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .message(message).build();
        return ResponseEntity.status(exceptionResponse.getStatusCode()).body(exceptionResponse);

    }

    @ExceptionHandler({
            EntityAlreadyExistsException.class,
    })
    public ResponseEntity<ExceptionDTO> handleConflictException(RuntimeException e) {
        String message = e.getMessage() == null ? e.getClass().getSimpleName() : e.getMessage();
        ExceptionDTO exceptionResponse = ExceptionDTO.builder()
                .statusCode(HttpStatus.CONFLICT.value())
                .status(HttpStatus.CONFLICT)
                .timestamp(LocalDateTime.now())
                .message(message).build();
        return ResponseEntity.status(exceptionResponse.getStatusCode()).body(exceptionResponse);

    }

}
