package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.duke.Duke;

public class DukeTest {
    @Test
    public void dummyTest() {
        Duke duke = new Duke("data/tasks.txt");
        assertEquals(2, 2);
    }
}
