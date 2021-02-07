package duke.task;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class DeadlineTest {

    @Test
    void addTask() {
    }

    @Test
    void testToString() {
        Deadline ddl = new Deadline("play badminton", LocalDate.of(2020, 01, 22));
        assertEquals("[D][ ] play badminton (by: JANUARY 22 2020)", ddl.toString());
    }
}