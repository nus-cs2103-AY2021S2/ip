package duke;

import duke.command.CommandList;
import duke.command.CommandParser;
import duke.task.Deadline;
import duke.task.taskState;
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
        assertEquals(taskState.DONE, deadline.getState());
    }

    @Test
    public void parseCommandTest_givenEventWithDate_commandIsEqual() {
        CommandParser commandParserA = new CommandParser(CommandList.EVENT, "Eat Cake /at Mar 20 2021");
        try {
            CommandParser commandParserB = CommandParser.parseLine("eVenT Eat Cake /at Mar 20 2021", 0);
            assertEquals(commandParserA.getData(), commandParserB.getData());
            assertEquals(commandParserA.getCommand(), commandParserB.getCommand());
        } catch (DukeException e) {
            fail();
        }
    }
}
