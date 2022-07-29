package ru.task_manager.controllers.exception_controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.task_manager.exceptions.EmailAlreadyExistsException;
import ru.task_manager.exceptions.UserNotFoundException;

@ControllerAdvice
public class UserExceptionController {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleInvalidParameterException(EmailAlreadyExistsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
