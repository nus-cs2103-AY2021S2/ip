package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void bye() {
        TaskList tl = new TaskList();
        Duke duke = new Duke("data/duke.txt");
        tl.bye(duke);
        assertEquals(duke.isOn, true);
    }
}