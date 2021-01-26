package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UiTest {

    @Test
    void addTask() {
        Duke duke = new Duke("data/duke.txt");
        Ui ui = new Ui();
        Event event = new Event("event party /at 5pm");
        String expected = Duke.line + "\n" + " Got it. I've added this task: \n"
                + "[E][ ] party at: 5pm" + "\n Now you have " + "1"
                + " tasks in the list" + "\n" + Duke.line;
        assertEquals(expected, ui.addTask(event,1));
    }
}