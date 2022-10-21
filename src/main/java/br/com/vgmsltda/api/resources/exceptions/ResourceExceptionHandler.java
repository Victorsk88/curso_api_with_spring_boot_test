package br.com.vgmsltda.api.resources.exceptions;

import br.com.vgmsltda.api.services.exceptions.DataIntegrityViolationException;
import br.com.vgmsltda.api.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError>
    objectNotFound(ObjectNotFoundException ex, HttpServletRequest request){
        StandardError standardError =
                new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value()
                ,ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException
            (DataIntegrityViolationException ex, HttpServletRequest request) {
        StandardError standardError = new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value()
                , ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

}
