package duke;

import duke.command.CommandList;
import duke.command.ParseCommands;
import duke.task.Deadline;
import duke.task.Task_State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DukeTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void taskTest() {
        Deadline deadline = new Deadline("Bake Cake", "Mar 20 2021");
        deadline.doTask();
        assertEquals(Task_State.DONE, deadline.getState());
    }

    @Test
    public void parseCommandTest() {
        ParseCommands parseCommandsA = new ParseCommands(CommandList.EVENT, "Eat Cake /at Mar 20 2021");
        try {
            ParseCommands parseCommandsB = ParseCommands.parseLine("eVenT Eat Cake /at Mar 20 2021", 0);
            assertEquals(parseCommandsA.getData(), parseCommandsB.getData());
            assertEquals(parseCommandsA.getCommand(), parseCommandsB.getCommand());
        } catch (DukeException e) {
            fail();
        }
    }
}
