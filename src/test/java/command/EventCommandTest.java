package command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import commands.Command;
import commands.DeadlineCommand;
import commands.EventCommand;
import org.junit.jupiter.api.Test;
import tasklist.TaskList;


public class EventCommandTest {
    private TaskList dummyTaskList = new TaskList();

    @Test
    public void testMissingArgs() {
        String s = "event junit test /at 30-04 6PM";
        Command c = new EventCommand(s);
        c.run(dummyTaskList);
        assertEquals(true, c.hasRunSuccessfully());
    }

    @Test
    void testMissingTimeInfo() {
        String s = "event junit test /at";
        Command c = new EventCommand(s);
        c.run(dummyTaskList);
        assertEquals(false, c.hasRunSuccessfully());
    }

    @Test
    public void testMissingDelimiter() {
        String s = "event junit test 30-04 6PM";
        Command c = new EventCommand(s);
        c.run(dummyTaskList);
        assertEquals(false, c.hasRunSuccessfully());
    }

    @Test
    public void testMissingDesc() {
        String s = "/at 30-04 6PM";
        Command c = new EventCommand(s);
        c.run(dummyTaskList);
        c.debug();
        assertEquals(false, c.hasRunSuccessfully());
    }
}
