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
 * JUnit test for the <code>FindCommand</code> class in duke.commands
 */
public class TestFindCommand {
    private final TaskList tasks;
    private final FindCommand command;

    /**
     * Initializes a <code>FindCommand</code> instance and a <code>TaskList</code> instance for testing.
     */
    public TestFindCommand() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse("2021-02-06 23:30", formatter);

        this.tasks = new TaskList();
        this.tasks.addTask(new ToDo("CS2103 Quiz 1"));
        this.tasks.addTask(new Deadline("BT4013 Quiz 2", dateTime));
        this.tasks.addTask(new Event("CS2103 Quiz 3", dateTime));

        this.command = new FindCommand("CS");
    }

    /**
     * Tests that <code>FindCommand</code> correctly determines whether to exit the application.
     */
    @Test
    public void testIsExit() {
        assertFalse(this.command.isExit());
    }

    /**
     * Tests that <code>FindCommand</code> (correctly) does not change the input <code>TaskList</code>.
     */
    @Test
    public void testExecute() {
        TaskList oldTaskList = HelperFunctions.deepCopyTaskList(this.tasks);
        this.command.execute(this.tasks);
        assertTrue(HelperFunctions.taskListsAreEqual(oldTaskList, this.tasks));
    }

    /**
     * Tests that <code>FindCommand</code> computes the response message correctly.
     */
    @Test
    public void testResponse() {
        String expectedResponse = "Here are the matching tasks in your list:\n"
                + "1.[T][ ] CS2103 Quiz 1\n"
                + "2.[E][ ] CS2103 Quiz 3 (at: 2021-02-06 23:30)\n";
        assertEquals(expectedResponse, this.command.getResponse(this.tasks));

        String expectedEmptyResponse = "There are no tasks matching the string 'CS' in your list :O";
        assertEquals(expectedEmptyResponse, this.command.getResponse(new TaskList()));
    }
}
