package ru.task_manager.utils;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.task_manager.exceptions.PropertiesReadException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

@Component
public class CommandPropertiesReader {
    private final Properties commandInfo = new Properties();
    @Getter
    private final Set<Object> keySet;

    public CommandPropertiesReader() throws PropertiesReadException {
        InputStream inputStream = this.getClass().getResourceAsStream("/command_info.properties");
        try {
            commandInfo.load(inputStream);
            keySet = commandInfo.keySet();
        } catch (IOException e) {
            throw new PropertiesReadException();
        }
    }

    public String getCommandInfo(String commandName){
        return commandInfo.getProperty(commandName);
    }
}
