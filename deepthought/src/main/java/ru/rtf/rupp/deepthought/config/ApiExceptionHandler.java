package ru.rtf.rupp.deepthought.config;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorDTO> handleAuthenticationException(AuthenticationException ex){
        logException(ex);
        return new ResponseEntity<>(new ErrorDTO(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), ex.getClass().getName()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleEntityNotFound(EntityNotFoundException ex){
        logException(ex);
        return new ResponseEntity<>(new ErrorDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage(), ex.getClass().getName()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ErrorDTO> handleEntityExistsException(EntityExistsException ex){
        logException(ex);
        return new ResponseEntity<>(new ErrorDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ex.getClass().getName()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        logException(ex);
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(new ValidationErrorDTO(HttpStatus.BAD_REQUEST.value(), errors), HttpStatus.BAD_REQUEST);
    }

    private void logException(Exception ex){
        log.error(ex.getMessage() + " | " + ex.getClass().getName());
    }
}


@Data
@AllArgsConstructor
class ValidationErrorDTO{
    private Integer code;
    private Map<String, String> errors;
}

@Data
@AllArgsConstructor
class ErrorDTO {
    private Integer code;
    private String msg;
    private String error;
}