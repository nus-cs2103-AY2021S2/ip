import commands.ByeCommand;
import org.junit.jupiter.api.Test;
import tasklist.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByeCommandTest {
    private TaskList dummyTaskList = new TaskList();

    @Test
    public void testByeSentExitSignal() {
        ByeCommand b = new ByeCommand("");
        b.run(dummyTaskList);
        assertEquals(true, b.hasSentExitDukeSignal());
    }

    @Test
    public void runBye() {
        ByeCommand b = new ByeCommand("");
        b.run(dummyTaskList);
        assertEquals(true, b.hasRunSuccessfully());
    }
}
