package ru.task_manager.controllers.exception_controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.task_manager.exceptions.*;

@ControllerAdvice
public class GeneralExceptionController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleInvalidParameterException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(e.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return new ResponseEntity<>(
                String.format("Method '%s' is not allowed!", e.getMethod()), HttpStatus.METHOD_NOT_ALLOWED);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(CommandExecutionException.class)
    public ResponseEntity<String> handleCommandExecutionException(CommandExecutionException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(FieldParseException.class)
    public ResponseEntity<String> handleFieldParseException(FieldParseException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(IncorrectCommandException.class)
    public ResponseEntity<String> handleIncorrectCommandException(IncorrectCommandException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(PropertiesReadException.class)
    public ResponseEntity<String> handlePropertiesReadException(PropertiesReadException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
