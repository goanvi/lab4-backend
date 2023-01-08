package goanvi.web.weblab3.exceptions;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@Log4j2
@ControllerAdvice
public class ExceptionManager { //TODO: изменить коды ошибок для респонзов

    @ExceptionHandler(IdentificationException.class)
    public ResponseEntity<String> handleTestException(IdentificationException e){
        log.error(e);
        return ResponseEntity.status(404).body("IdentificationException: "+e.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error(e);
        return ResponseEntity.status(404).body("Invalid input data");
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException e){
        log.error(e);
        return ResponseEntity.status(404).body("User already exists");
    }

}

