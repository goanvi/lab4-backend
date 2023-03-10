package goanvi.web.lab4_backend.exceptions;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@Log4j2
@ControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(IdentificationException.class)
    public ResponseEntity<String> handleTestException(IdentificationException e){
        log.error(e);
        return ResponseEntity.status(400).body("IdentificationException: "+e.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error(e);
        return ResponseEntity.status(400).body("Invalid input data");
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException e){
        log.error(e);
        return ResponseEntity.status(400).body("User already exists");
    }

}

