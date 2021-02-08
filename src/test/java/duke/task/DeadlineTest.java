package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void fileString_correctDateNoTime_outputCorrectly() {
        assertEquals("D | false | test | 2020-01-27", new Deadline("test", "2020-01-27").toFileString());
    }

    @Test
    public void fileString_correctDateWithTime_outputCorrectly() {
        assertEquals("D | false | test | 2020-01-27 0800", new Deadline("test", "2020-01-27 0800").toFileString());
    }
}
