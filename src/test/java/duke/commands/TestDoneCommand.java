package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

/**
 * JUnit test for the <code>DoneCommand</code> class in duke.commands
 */
public class TestDoneCommand {
    private final ToDo toDo;
    private final Deadline deadline;
    private final Event event;
    private final TaskList tasks;
    private final DoneCommand command;

    /**
     * Initializes a <code>DoneCommand</code> instance and a <code>TaskList</code> instance for testing.
     */
    public TestDoneCommand() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse("2021-02-06 23:30", formatter);

        this.tasks = new TaskList();
        this.toDo = new ToDo("CS2103 Quiz 1");
        this.deadline = new Deadline("BT4013 Quiz 2", dateTime);
        this.event = new Event("CS2103 Quiz 3", dateTime);

        this.deadline.markAsDone();

        this.tasks.addTask(this.toDo);
        this.tasks.addTask(this.deadline);
        this.tasks.addTask(this.event);

        this.command = new DoneCommand(3);
    }

    /**
     * Tests that <code>DoneCommand</code> correctly determines whether to exit the application.
     */
    @Test
    public void testIsExit() {
        assertFalse(this.command.isExit());
    }

    /**
     * Tests that <code>DoneCommand</code> alters the input <code>TaskList</code> correctly.
     */
    @Test
    public void testExecute() {
        assertEquals(3, this.tasks.getSize());
        assertEquals(this.toDo, this.tasks.getTaskByIndex(1));
        assertEquals(this.deadline, this.tasks.getTaskByIndex(2));
        assertEquals(this.event, this.tasks.getTaskByIndex(3));

        assertFalse(this.tasks.getTaskByIndex(1).isDone());
        assertTrue(this.tasks.getTaskByIndex(2).isDone());
        assertFalse(this.tasks.getTaskByIndex(3).isDone());

        this.command.execute(this.tasks);

        assertEquals(3, this.tasks.getSize());
        assertEquals(this.toDo, this.tasks.getTaskByIndex(1));
        assertEquals(this.deadline, this.tasks.getTaskByIndex(2));
        assertEquals(this.event, this.tasks.getTaskByIndex(3));

        assertFalse(this.tasks.getTaskByIndex(1).isDone());
        assertTrue(this.tasks.getTaskByIndex(2).isDone());
        assertTrue(this.tasks.getTaskByIndex(3).isDone());
    }

    /**
     * Tests that <code>DoneCommand</code> computes the response message correctly.
     */
    @Test
    public void testResponse() {
        this.command.execute(this.tasks);

        String expectedResponse = "Prrrr! I've marked this task as done:\n"
                + "\n"
                + "Status | Description          | Time            \n"
                + "------------------------------------------------\n"
                + "[E][X] | CS2103 Quiz 3        | 2021-02-06 23:30";
        assertEquals(expectedResponse, this.command.getResponse(this.tasks));

        DoneCommand invalidCommand = new DoneCommand(10);
        String expectedExceptionMessage = "Task 10 does not exist :O";
        assertEquals(expectedExceptionMessage, invalidCommand.getResponse(this.tasks));
    }
}
