package duke.task;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class EventTest {

    @Test
    void addTask() {
    }

    @Test
    void testToString() {
        Event event = new Event("play badminton", LocalDate.of(2020, 01, 22));
        assertEquals("[E][ ] play badminton (at: JANUARY 22 2020)", event.toString());
    }
}