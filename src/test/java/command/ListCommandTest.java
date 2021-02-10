package command;

import commands.ListCommand;
import org.junit.jupiter.api.Test;
import tasklist.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {
    private TaskList dummyTaskList = new TaskList();

    @Test
    public void runListNoArgs() {
        ListCommand c = new ListCommand("");
        c.run(dummyTaskList);
        assertEquals(true, c.hasRunSuccessfully());
        assertEquals(false, c.hasSentExitDukeSignal());
    }

    @Test
    public void runListWithArgs() {
        ListCommand c = new ListCommand("hjkdhjsda");
        c.run(dummyTaskList);
        assertEquals(false, c.hasRunSuccessfully());
        assertEquals(false, c.hasSentExitDukeSignal());
    }
}
