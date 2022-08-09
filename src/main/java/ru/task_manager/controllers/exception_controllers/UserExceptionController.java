package ru.task_manager.controllers.exception_controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.task_manager.exceptions.BusiestUserNotFoundException;
import ru.task_manager.exceptions.EmailAlreadyExistsException;
import ru.task_manager.exceptions.EmailNotFoundException;

@ControllerAdvice
public class UserExceptionController {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidParameterException(EmailAlreadyExistsException e) {
        return e.getMessage();
    }

    @ExceptionHandler(BusiestUserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBusiestUserNotFoundException(BusiestUserNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(EmailNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleEmailNotFoundException(EmailNotFoundException e) {
        return e.getMessage();
    }
}
