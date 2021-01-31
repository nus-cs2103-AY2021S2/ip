package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void stringConstructor_description() {
        Deadline d = new Deadline("homework", "31 Jan 21 2359");
        assertEquals("homework", d.getDescription());
    }

    @Test
    public void stringConstructor_date() {
        Deadline d = new Deadline("homework", "31 Jan 21 2359");
        assertEquals("31 Jan 2021, 11:59 PM", d.getDateTime());
    }
}
