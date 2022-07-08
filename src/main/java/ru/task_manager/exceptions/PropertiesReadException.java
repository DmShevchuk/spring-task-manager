package ru.task_manager.exceptions;

public class PropertiesReadException extends Exception{
    public PropertiesReadException(){
        super("Unable to reach ,properties file with info about commands!");
    }
}
