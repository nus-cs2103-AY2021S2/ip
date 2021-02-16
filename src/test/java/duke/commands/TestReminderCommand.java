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


/**
 * JUnit test for the <code>ReminderCommand</code> class in duke.commands
 */
public class TestReminderCommand {
    private final TaskList tasks;

    private final String twoDaysAfter;
    private final String oneDayBefore;

    /**
     * Initializes a <code>ReminderCommand</code> instance and a <code>TaskList</code>
     * instance for testing.
     */
    public TestReminderCommand() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");

        LocalDateTime twoDaysAfterDateTime = LocalDateTime.now().plusDays(2);
        this.twoDaysAfter = twoDaysAfterDateTime.format(formatter);

        LocalDateTime oneDayBeforeDateTime = LocalDateTime.now().minusDays(1);
        this.oneDayBefore = oneDayBeforeDateTime.format(formatter);

        Deadline overdueDeadline = new Deadline("CS2103 Quiz 1", oneDayBeforeDateTime);
        Deadline goodDeadline = new Deadline("CS2103 Quiz 2", twoDaysAfterDateTime);
        Deadline doneDeadline = new Deadline("CS2103 Quiz 3", oneDayBeforeDateTime);
        doneDeadline.markAsDone();

        Event overdueEvent = new Event("CS2103 Quiz 4", oneDayBeforeDateTime);
        Event goodEvent = new Event("CS2103 Quiz 5", twoDaysAfterDateTime);
        Event doneEvent = new Event("CS2103 Quiz 6", oneDayBeforeDateTime);
        doneEvent.markAsDone();

        TaskList tasks = new TaskList();
        tasks.addTask(overdueDeadline);
        tasks.addTask(goodDeadline);
        tasks.addTask(doneDeadline);
        tasks.addTask(overdueEvent);
        tasks.addTask(goodEvent);
        tasks.addTask(doneEvent);
        this.tasks = tasks;
    }

    /**
     * Tests that <code>ReminderCommand</code> correctly determines whether to exit the application.
     */
    @Test
    public void testIsExit() {
        assertFalse(new ReminderCommand(5).isExit());
    }

    /**
     * Tests that <code>ReminderCommand</code>'s execute method alters the input <code>TaskList</code> correctly.
     */
    @Test
    public void testExecute() {
        ReminderCommand command = new ReminderCommand(5);
        TaskList oldTaskList = HelperFunctions.deepCopyTaskList(this.tasks);
        command.execute(this.tasks);
        assertTrue(HelperFunctions.taskListsAreEqual(oldTaskList, this.tasks));
    }

    /**
     * Tests that <code>ReminderCommand</code> computes the response message correctly.
     */
    @Test
    public void testResponse() {
        // "Relaxed" because tasks will only be flagged out as urgent if there are less than 1 day left.
        ReminderCommand relaxedCommand = new ReminderCommand(1);
        String expectedRelaxed = "Uh oh... You have 2 overdue task(s):\n"
                + "\n"
                + "Status   | Description          | Time            \n"
                + "--------------------------------------------------\n"
                + "1.[D][ ] | CS2103 Quiz 1        | " + this.oneDayBefore + "\n"
                + "2.[E][ ] | CS2103 Quiz 4        | " + this.oneDayBefore + "\n"
                + "\n"
                + "\n"
                + "You do not have any urgent tasks!\n";
        assertEquals(expectedRelaxed, relaxedCommand.getResponse(this.tasks));

        // "Urgent" because tasks will be flagged out as urgent if there are 10 or less days left.
        ReminderCommand urgentCommand = new ReminderCommand(10);
        String expectedUrgent = "Uh oh... You have 2 overdue task(s):\n"
                + "\n"
                + "Status   | Description          | Time            \n"
                + "--------------------------------------------------\n"
                + "1.[D][ ] | CS2103 Quiz 1        | " + this.oneDayBefore + "\n"
                + "2.[E][ ] | CS2103 Quiz 4        | " + this.oneDayBefore + "\n"
                + "\n"
                + "\n"
                + "You have 2 task(s) due in less than 10 day(s):\n"
                + "\n"
                + "Status   | Description          | Time            \n"
                + "--------------------------------------------------\n"
                + "1.[D][ ] | CS2103 Quiz 2        | " + this.twoDaysAfter + "\n"
                + "2.[E][ ] | CS2103 Quiz 5        | " + this.twoDaysAfter + "\n";
        assertEquals(expectedUrgent, urgentCommand.getResponse(this.tasks));

        String expectedEmpty = "You do not have any overdue tasks!\n" + "\n" + "You do not have any urgent tasks!\n";
        assertEquals(expectedEmpty, urgentCommand.getResponse(new TaskList()));
    }
}
