package ru.task_manager.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LineHandlerTest {
    private LineHandler lineHandler;
    @BeforeEach
    void setUp() {
        this.lineHandler = new LineHandler();
    }

    @Test
    @DisplayName("Parsing test on empty string")
    void testParse_EmptyString() throws IOException {
        assertArrayEquals(new String[]{""}, lineHandler.parse(""));
    }

    @Test
    @DisplayName("Parsing test on command name")
    void testParse_ArgQuantity_1() throws IOException{
        assertArrayEquals(new String[]{"command_name"}, lineHandler.parse("command_name"));
    }

    @Test
    @DisplayName("Parsing test on full command")
    void testParse_ArgQuantity_5() throws IOException{
        assertArrayEquals(new String[]{"command_name", "title", "description", "20.09.2022", "1", "in progress"},
                lineHandler.parse("command_name, title, description, 20.09.2022, 1, in progress"));
    }

    @Test
    @DisplayName("Parsing test on null")
    void testParse_ArgIsNull() {
        assertThrows(IOException.class, () -> lineHandler.parse(null));
    }
}