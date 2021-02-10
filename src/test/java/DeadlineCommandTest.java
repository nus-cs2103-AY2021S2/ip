import commands.Command;
import commands.DeadlineCommand;
import org.junit.jupiter.api.Test;
import tasklist.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineCommandTest {
    private TaskList dummyTaskList = new TaskList();

    @Test
    public void testMissingArgs() {
        String s = "junit test /by 30-04 6PM";
        Command c = new DeadlineCommand(s);
        c.run(dummyTaskList);
        assertEquals(true, c.hasRunSuccessfully());
    }

    @Test
    void testMissingTimeInfo() {
        String s = "junit test /by";
        Command c = new DeadlineCommand(s);
        c.run(dummyTaskList);
        assertEquals(false, c.hasRunSuccessfully());
    }

    @Test
    public void testMissingDelimiter() {
        String s = "junit test 30-04 6PM";
        Command c = new DeadlineCommand(s);
        c.run(dummyTaskList);
        assertEquals(false, c.hasRunSuccessfully());
    }

    @Test
    public void testMissingDesc() {
        String s = "/by 30-04 6PM";
        Command c = new DeadlineCommand(s);
        c.run(dummyTaskList);
        c.debug();
        assertEquals(false, c.hasRunSuccessfully());
    }
}
