package ru.task_manager.exceptions;

public class BusiestUserNotFoundException extends RuntimeException{
    public BusiestUserNotFoundException() {
        super("Unable to find users with such task parameters!");
    }
}
