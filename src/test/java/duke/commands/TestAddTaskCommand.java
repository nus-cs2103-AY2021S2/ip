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
 * JUnit test for the <code>AddTaskCommand</code> class in duke.commands
 */
public class TestAddTaskCommand {
    private final ToDo toDo;
    private final Deadline deadline;
    private final Event event;
    private final AddTaskCommand addToDoCommand;
    private final AddTaskCommand addDeadlineCommand;
    private final AddTaskCommand addEventCommand;


    /**
     * Initializes a <code>AddTaskCommand</code> instance for testing.
     */
    public TestAddTaskCommand() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse("2021-02-06 23:30", formatter);

        this.toDo = new ToDo("CS2103 Quiz 1");
        this.deadline = new Deadline("BT4013 Quiz 2", dateTime);
        this.event = new Event("CS2103 Quiz 3", dateTime);

        this.addToDoCommand = new AddTaskCommand(this.toDo);
        this.addDeadlineCommand = new AddTaskCommand(this.deadline);
        this.addEventCommand = new AddTaskCommand(this.event);
    }

    /**
     * Tests that <code>AddTaskCommand</code> correctly determines whether to exit the application.
     */
    @Test
    public void testIsExit() {
        assertFalse(this.addToDoCommand.isExit());
        assertFalse(this.addDeadlineCommand.isExit());
        assertFalse(this.addEventCommand.isExit());
    }

    /**
     * Tests that <code>AddTaskCommand</code> alters the input <code>TaskList</code> correctly.
     */
    @Test
    public void testExecute() {
        TaskList tasks = new TaskList();

        this.addToDoCommand.execute(tasks);
        assertEquals(1, tasks.getSize());
        assertEquals(this.toDo, tasks.getTaskByIndex(1));

        this.addDeadlineCommand.execute(tasks);
        assertEquals(2, tasks.getSize());
        assertEquals(this.toDo, tasks.getTaskByIndex(1));
        assertEquals(this.deadline, tasks.getTaskByIndex(2));

        this.addEventCommand.execute(tasks);
        assertEquals(3, tasks.getSize());
        assertEquals(this.toDo, tasks.getTaskByIndex(1));
        assertEquals(this.deadline, tasks.getTaskByIndex(2));
        assertEquals(this.event, tasks.getTaskByIndex(3));
    }

    /**
     * Tests that <code>AddTaskCommand</code> computes the response message correctly.
     */
    @Test
    public void testResponse() {
        TaskList tasks = new TaskList();

        this.addToDoCommand.execute(tasks);
        String expectedAddToDoResponse = "Got it. I've added this task:\n"
                + "[T][ ] CS2103 Quiz 1\n"
                + "Now you have 1 task(s) in the list.";
        assertEquals(expectedAddToDoResponse, this.addToDoCommand.getResponse(tasks));

        this.addDeadlineCommand.execute(tasks);
        String expectedAddDeadlineResponse = "Got it. I've added this task:\n"
                + "[D][ ] BT4013 Quiz 2 (by: 2021-02-06 23:30)\n"
                + "Now you have 2 task(s) in the list.";
        assertEquals(expectedAddDeadlineResponse, this.addDeadlineCommand.getResponse(tasks));

        this.addEventCommand.execute(tasks);
        String expectedAddEventResponse = "Got it. I've added this task:\n"
                + "[E][ ] CS2103 Quiz 3 (at: 2021-02-06 23:30)\n"
                + "Now you have 3 task(s) in the list.";
        assertEquals(expectedAddEventResponse, this.addEventCommand.getResponse(tasks));
    }
}
