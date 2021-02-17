package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.helper.HelperFunctions;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

/**
 * JUnit test for the <code>ListCommand</code> class in duke.commands
 */
public class TestListCommand {
    private final TaskList tasks;
    private final ListCommand command;

    /**
     * Initializes a <code>ListCommand</code> instance and a <code>TaskList</code> instance for testing.
     */
    public TestListCommand() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse("2021-02-06 23:30", formatter);

        this.tasks = new TaskList();
        this.tasks.addTask(new ToDo("CS2103 Quiz 1"));
        this.tasks.addTask(new Deadline("BT4013 Quiz 2", dateTime));
        this.tasks.addTask(new Event("CS2103 Quiz 3", dateTime));

        this.command = new ListCommand();
    }

    /**
     * Tests that <code>ListCommand</code> correctly determines whether to exit the application.
     */
    @Test
    public void testIsExit() {
        assertFalse(this.command.isExit());
    }

    /**
     * Tests that <code>ListCommand</code> (correctly) does not change the input <code>TaskList</code>.
     */
    @Test
    public void testExecute() {
        TaskList oldTaskList = HelperFunctions.deepCopyTaskList(this.tasks);
        this.command.execute(this.tasks);
        assertTrue(HelperFunctions.taskListsAreEqual(oldTaskList, this.tasks));
    }

    /**
     * Tests that <code>ListCommand</code> computes the response message correctly.
     */
    @Test
    public void testResponse() {
        String expectedResponse = "Here are the task(s) in your list:\n"
                + "\n"
                + "Status   | Description          | Time            \n"
                + "--------------------------------------------------\n"
                + "1.[T][ ] | CS2103 Quiz 1        | \n"
                + "2.[D][ ] | BT4013 Quiz 2        | 2021-02-06 23:30\n"
                + "3.[E][ ] | CS2103 Quiz 3        | 2021-02-06 23:30";
        assertEquals(expectedResponse, this.command.getResponse(this.tasks));

        String expectedEmptyResponse = "You have no tasks in your list yet :X";
        assertEquals(expectedEmptyResponse, this.command.getResponse(new TaskList()));
    }
}
