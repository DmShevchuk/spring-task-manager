package ru.task_manager.controllers.exception_controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.task_manager.exceptions.*;

import java.util.Objects;

@ControllerAdvice
public class GeneralExceptionController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidParameterException(MethodArgumentNotValidException e) {
        return Objects.requireNonNull(e.getFieldError()).getDefaultMessage();
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return String.format("Method '%s' is not allowed!", e.getMethod());
    }


    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(CommandExecutionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleCommandExecutionException(CommandExecutionException e) {
        return e.getMessage();
    }


    @ExceptionHandler(FieldParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleFieldParseException(FieldParseException e) {
        return e.getMessage();
    }


    @ExceptionHandler(IncorrectCommandException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIncorrectCommandException(IncorrectCommandException e) {
        return e.getMessage();
    }


    @ExceptionHandler(PropertiesReadException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handlePropertiesReadException(PropertiesReadException e) {
        return e.getMessage();
    }
}
