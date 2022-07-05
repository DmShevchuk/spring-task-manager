package ru.task_manager.exceptions;

public class IncorrectArgsQuantityException extends RuntimeException{
    public IncorrectArgsQuantityException(int requiredQuantity, int providedQuantity){
        super(String.format("Incorrect quantity of arguments! Requires %d, provided %d.", requiredQuantity, providedQuantity));
    }
}
