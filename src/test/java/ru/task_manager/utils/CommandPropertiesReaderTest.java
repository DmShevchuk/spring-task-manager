package ru.task_manager.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.task_manager.exceptions.PropertiesReadException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class CommandPropertiesReaderTest {
    private CommandPropertiesReader commandPropertiesReader;
    Properties actualProperties = new Properties();
    private Set<Object> actualCommandInfoSet;

    @BeforeEach
    void setUp() throws PropertiesReadException {
        this.commandPropertiesReader = new CommandPropertiesReader();
    }

    @Test
    @DisplayName("Get info about command by name")
    void getCommandInfo() {
        getActualCommandInfo();
        for (Object o : actualCommandInfoSet) {
            assertEquals(actualProperties.getProperty(o.toString()),
                    commandPropertiesReader.getCommandInfo(o.toString()));
        }
    }

    @Test
    @DisplayName("Number of command and their names are the same")
    void getKeySet() {
        getActualCommandInfo();
        assertIterableEquals(actualCommandInfoSet, commandPropertiesReader.getKeySet());
    }

    void getActualCommandInfo() {
        InputStream inputStream = this.getClass().getResourceAsStream("/command_info.properties");
        try {
            actualProperties.load(inputStream);
            actualCommandInfoSet = actualProperties.keySet();
        } catch (IOException ignore) {
        }
    }
}