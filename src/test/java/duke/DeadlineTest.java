package duke;

import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    @Test
    void testDeadlineDescription() {
        assertEquals(new Deadline("math assignment",
                LocalDateTime.parse("2020-11-27 2359", formatter)).getDescription(),
                "math assignment"
        );
    }

    @Test
    void testDeadlineDone() {
        Deadline underTest = new Deadline("physics experiment",
                LocalDateTime.parse("2020-11-29 2359", formatter));
        underTest.setDone();
        underTest.setNotDone();
        assertEquals(underTest.isDone(), false);
    }

    @Test
    void testDateTime() {
        assertEquals(new Deadline("coding assignment",
                LocalDateTime.parse("2020-12-02 1920", formatter)).getDateTime(),
                "2020-12-02 1920"
        );
    }
}