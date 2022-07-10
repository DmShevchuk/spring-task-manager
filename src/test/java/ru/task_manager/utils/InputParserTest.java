package ru.task_manager.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.task_manager.exceptions.FieldParseException;
import ru.task_manager.factories.TaskType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class InputParserTest {
    @DisplayName("Tests for method parseLong")
    static class ParseLongTest {
        private InputParser inputParser;

        @BeforeEach
        void setUp() {
            this.inputParser = new InputParser();
        }

        @Test
        @DisplayName("Parsing of 0L")
        void parseLongZero() {
            assertEquals(0L, inputParser.parseLong("0"));
        }

        @Test
        @DisplayName("Parsing of negative numbers")
        void parseLongNegativeNumbers() {
            assertAll(
                    () -> assertEquals(1L, inputParser.parseLong("1")),
                    () -> assertEquals(2L, inputParser.parseLong("2")),
                    () -> assertEquals(100L, inputParser.parseLong("100")),
                    () -> assertEquals(1200L, inputParser.parseLong("1200")),
                    () -> assertEquals(105700L, inputParser.parseLong("105700"))
            );
        }

        @Test
        @DisplayName("Parsing of positive numbers")
        void parseLongPositiveNumbers() {
            assertAll(
                    () -> assertEquals(-1L, inputParser.parseLong("-1")),
                    () -> assertEquals(-2L, inputParser.parseLong("-2")),
                    () -> assertEquals(-100L, inputParser.parseLong("-100")),
                    () -> assertEquals(-1200L, inputParser.parseLong("-1200")),
                    () -> assertEquals(-105700L, inputParser.parseLong("-105700"))
            );
        }

        @Test
        @DisplayName("Parsing of float numbers")
        void parseLongFloatNumbers() {
            assertThrows(FieldParseException.class, () -> inputParser.parseLong("100.15"));
        }

        @Test
        @DisplayName("Parsing of empty string")
        void parseLongEmptyString() {
            assertThrows(FieldParseException.class, () -> inputParser.parseLong(""));
        }

        @Test
        @DisplayName("Parsing of strings with characters")
        void parseLongStringsWithCharacters() {
            assertThrows(FieldParseException.class, () -> inputParser.parseLong("ABC"));
        }

        @Test
        @DisplayName("Parsing of null")
        void parseLongNull() {
            assertThrows(FieldParseException.class, () -> inputParser.parseLong(null));
        }
    }

    @DisplayName("Tests for method parseString")
    static class ParseStringTest{
        private InputParser inputParser;

        @BeforeEach
        void setUp() {
            this.inputParser = new InputParser();
        }

        @Test
        @DisplayName("Parsing of empty string")
        void parseEmptyString(){
            assertThrows(FieldParseException.class, () -> inputParser.parseString(""));
        }

        @Test
        @DisplayName("Parsing of null")
        void parseNullString(){
            assertThrows(FieldParseException.class, () -> inputParser.parseString(null));
        }

        @Test
        @DisplayName("Parsing of strings with characters")
        void parseCorrectString(){
            assertAll(
                    () -> assertEquals("title", inputParser.parseString("title")),
                    () -> assertEquals("description", inputParser.parseString("description")),
                    () -> assertEquals("line", inputParser.parseString("line")),
                    () -> assertEquals("line with ,!", inputParser.parseString("line with ,!")),
                    () -> assertEquals("line with numbers 12 123 145",
                            inputParser.parseString("line with numbers 12 123 145"))
            );
        }
    }

    @DisplayName("Tests for method parseDate")
    static class ParseDateTest{
        private InputParser inputParser;

        @BeforeEach
        void setUp(){
            this.inputParser = new InputParser();
        }

        @Test
        @DisplayName("Parsing of null")
        void parseDateFromNull(){
            assertThrows(FieldParseException.class, () -> inputParser.parseDate(null));
        }

        @Test
        @DisplayName("Parsing of empty string")
        void parseDateFromEmptyString(){
            assertThrows(FieldParseException.class, () -> inputParser.parseDate(""));
        }

        @Test
        @DisplayName("Parsing of string with characters")
        void parseDateFromStringWithCharacters(){
            assertThrows(FieldParseException.class, () -> inputParser.parseDate("Line with characters"));
        }

        @Test
        @DisplayName("Parsing of date with invalid format")
        void parseDateWithInvalidFormat(){
            assertAll(
                    () -> assertThrows(FieldParseException.class, () -> inputParser.parseDate("25-09-2022")),
                    () -> assertThrows(FieldParseException.class,
                            () -> inputParser.parseDate("02-1-2018 06:07:59")),
                    () -> assertThrows(FieldParseException.class, () -> inputParser.parseDate("12-12-2021")),
                    () -> assertThrows(FieldParseException.class,
                            () -> inputParser.parseDate("Tue, 02 Jan 2018 18:07:59 IST")),
                    () -> assertThrows(FieldParseException.class, () -> inputParser.parseDate("01/02/2018"))
            );
        }

        @Test
        @DisplayName("Parsing of date with invalid numbers (ex: day > 31, month > 12)")
        void parseDateWithInvalidNumbers() throws ParseException{
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

            Date dateFirstCase = formatter.parse("02.07.2022");
            Date dateSecondCase = formatter.parse("01.13.2022");
            Date dateThirdCase = formatter.parse("02.15.2021");
            Date dateFourthCase = formatter.parse("123.17.2020");
            Date dateFifthCase = formatter.parse("00.00.2021");

            assertAll(
                    () -> assertEquals(dateFirstCase, inputParser.parseDate("32.06.2022")),
                    () -> assertEquals(dateSecondCase, inputParser.parseDate("01.01.2023")),
                    () -> assertEquals(dateThirdCase, inputParser.parseDate("02.03.2022")),
                    () -> assertEquals(dateFourthCase, inputParser.parseDate("31.08.2021")),
                    () -> assertEquals(dateFifthCase, inputParser.parseDate("30.11.2020"))
            );
        }

        @Test
        @DisplayName("Parsing correct date")
        void parseDateWithCorrectFormat() throws ParseException {
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

            Date dateFirstCase = formatter.parse("22.06.2022");
            Date dateSecondCase = formatter.parse("01.01.1900");
            Date dateThirdCase = formatter.parse("1.1.1901");
            Date dateFourthCase = formatter.parse("12.12.2021");
            Date dateFifthCase = formatter.parse("05.05.2022");

            assertAll(
                    () -> assertEquals(dateFirstCase, inputParser.parseDate("22.06.2022")),
                    () -> assertEquals(dateSecondCase, inputParser.parseDate("01.01.1900")),
                    () -> assertEquals(dateThirdCase, inputParser.parseDate("1.1.1901")),
                    () -> assertEquals(dateFourthCase, inputParser.parseDate("12.12.2021")),
                    () -> assertEquals(dateFifthCase, inputParser.parseDate("05.05.2022"))
            );
        }
    }

    @DisplayName("Test for method parseTaskType")
    static class ParseTaskTypeTest{
        private InputParser inputParser;

        @BeforeEach
        void setUp(){
            this.inputParser = new InputParser();
        }

        @Test
        @DisplayName("Parse type from null")
        void parseTaskTypeFromNull(){
            assertThrows(FieldParseException.class, () -> inputParser.parseTaskType(null));
        }

        @Test
        @DisplayName("Parse type from empty string")
        void parseTaskTypeFromEmptyString(){
            assertEquals(TaskType.NEW, inputParser.parseTaskType(""));
        }

        @Test
        @DisplayName("Parse type from unresolved type of task")
        void parseTaskTypeFromUnresolvedTaskType(){
            TaskType newType = TaskType.NEW;

            assertAll(
                    () -> assertEquals(newType, inputParser.parseTaskType("type")),
                    () -> assertEquals(newType, inputParser.parseTaskType("finished")),
                    () -> assertEquals(newType, inputParser.parseTaskType("running")),
                    () -> assertEquals(newType, inputParser.parseTaskType("fixed")),
                    () -> assertEquals(newType, inputParser.parseTaskType("testing"))
            );
        }

        @Test
        @DisplayName("Parse types from correct strings")
        void parseTaskTypeFromCorrectTaskType(){
            assertAll(
                    () -> assertEquals(TaskType.NEW, inputParser.parseTaskType("new")),
                    () -> assertEquals(TaskType.DONE, inputParser.parseTaskType("done")),
                    () -> assertEquals(TaskType.IN_PROGRESS, inputParser.parseTaskType("in progress"))
            );

        }
    }
}