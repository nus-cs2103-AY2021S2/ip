package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

/**
 * JUnit test for the <code>DeleteCommand</code> class in duke.commands
 */
public class TestDeleteCommand {
    private final ToDo toDo;
    private final Deadline deadline;
    private final Event event;
    private final TaskList tasks;
    private final DeleteCommand command;

    /**
     * Initializes a <code>DeleteCommand</code> instance and a <code>TaskList</code> instance for testing.
     */
    public TestDeleteCommand() {
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

        this.command = new DeleteCommand(1);
    }

    /**
     * Tests that <code>DeleteCommand</code> correctly determines whether to exit the application.
     */
    @Test
    public void testIsExit() {
        assertFalse(this.command.isExit());
    }

    /**
     * Tests that <code>DeleteCommand</code> alters the input <code>TaskList</code> correctly.
     */
    @Test
    public void testExecute() {
        assertEquals(3, this.tasks.getSize());
        assertEquals(this.toDo, this.tasks.getTaskByIndex(1));
        assertEquals(this.deadline, this.tasks.getTaskByIndex(2));
        assertEquals(this.event, this.tasks.getTaskByIndex(3));

        this.command.execute(this.tasks);

        assertEquals(2, this.tasks.getSize());
        assertEquals(this.deadline, this.tasks.getTaskByIndex(1));
        assertEquals(this.event, this.tasks.getTaskByIndex(2));
    }

    /**
     * Tests that <code>DeleteCommand</code> computes the response message correctly.
     */
    @Test
    public void testResponse() {
        this.command.execute(this.tasks);
        String expectedResponse = "Noted. I've removed this task:\n" + "[T][ ] CS2103 Quiz 1";
        assertEquals(expectedResponse, this.command.getResponse(this.tasks));

        DeleteCommand invalidCommand = new DeleteCommand(10);
        String expectedExceptionMessage = "Task 10 does not exist :O";
        assertEquals(expectedExceptionMessage, invalidCommand.getResponse(this.tasks));
    }
}
