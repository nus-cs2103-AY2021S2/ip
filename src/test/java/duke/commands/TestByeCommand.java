package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.helper.HelperFunctions;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

/**
 * JUnit test for the <code>ByeCommand</code> class in duke.commands
 */
public class TestByeCommand {
    private final TaskList tasks;
    private final ByeCommand command;

    /**
     * Initializes a <code>ByeCommand</code> instance for testing.
     */
    public TestByeCommand() {
        this.command = new ByeCommand();
        this.tasks = new TaskList();
        this.tasks.addTask(new ToDo("CS2103 Quiz"));
        this.tasks.addTask(new Deadline("CS2103 Quiz", LocalDateTime.now()));
        this.tasks.addTask(new Event("CS2103 Quiz", LocalDateTime.now()));
    }

    /**
     * Tests that <code>ByeCommand</code> correctly determines whether to exit the application.
     */
    @Test
    public void testIsExit() {
        assertTrue(this.command.isExit());
    }

    /**
     * Tests that <code>ByeCommand</code> (correctly) does not change the input <code>TaskList</code>.
     */
    @Test
    public void testExecute() {
        TaskList oldTaskList = HelperFunctions.deepCopyTaskList(this.tasks);
        this.command.execute(this.tasks);
        assertTrue(HelperFunctions.taskListsAreEqual(oldTaskList, this.tasks));
    }

    /**
     * Tests that <code>ByeCommand</code> computes the response message correctly.
     */
    @Test
    public void testResponse() {
        String expectedResponse = "Bye. Hope to see you again soon!";
        assertEquals(expectedResponse, this.command.getResponse(this.tasks));
    }
}
