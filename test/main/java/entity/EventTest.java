package main.java.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testToString() {
        Task task = new Event("celebrate new year", "on", "3rd feb");
        assertEquals("[E][ ] celebrate new year (on: 3rd feb)", task.toString());
        Task task2 = new Event("celebrate new year again", "on", LocalDate.parse("2020-11-23"));
        assertEquals("[E][ ] celebrate new year again (on: Nov 23 2020)", task2.toString());
        Task task3 = new Event("celebrate new year again", "on", LocalDate.parse("2020-11-23"), true);
        assertEquals("[E][X] celebrate new year again (on: Nov 23 2020)", task3.toString());
    }

    @Test
    void toFileString() {
        Task task = new Event("celebrate new year", "on", "3rd feb");
        assertEquals("E|0|celebrate new year|on|3rd feb", task.toFileString());
        Task task2 = new Event("celebrate new year again", "on", LocalDate.parse("2020-11-23"));
        assertEquals("E|0|celebrate new year again|on|2020-11-23", task2.toFileString());
        Task task3 = new Event("celebrate new year again", "on", LocalDate.parse("2020-11-23"), true);
        assertEquals("E|1|celebrate new year again|on|2020-11-23", task3.toFileString());
    }
}