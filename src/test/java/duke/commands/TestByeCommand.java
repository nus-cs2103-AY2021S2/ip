package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.tasks.TaskList;
import duke.tasks.ToDo;

/**
 * JUnit test for the <code>ByeCommand</code> class in duke.commands
 */
public class TestByeCommand {
    private final ToDo toDo;
    private final TaskList tasks;
    private final ByeCommand command;

    /**
     * Initializes a <code>ByeCommand</code> instance for testing.
     */
    public TestByeCommand() {
        this.command = new ByeCommand();
        this.tasks = new TaskList();

        this.toDo = new ToDo("CS2103 Quiz");
        this.tasks.addTask(this.toDo);
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
        assertEquals(1, this.tasks.getSize());
        assertEquals(this.toDo, this.tasks.getTaskByIndex(1));

        this.command.execute(this.tasks);

        assertEquals(1, this.tasks.getSize());
        assertEquals(this.toDo, this.tasks.getTaskByIndex(1));
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
