package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Parser;


public class TestInvalidInputCommand {
    private final ToDo toDo;
    private final TaskList tasks;

    /**
     * Initialize a <code>InvalidInputCommand</code> instance for testing.
     */
    public TestInvalidInputCommand() {
        this.tasks = new TaskList();
        this.toDo = new ToDo("CS2103 Quiz");
        this.tasks.addTask(this.toDo);
    }

    /**
     * Test that <code>InvalidInputCommand</code> correctly determines whether to exit the application.
     */
    @Test
    public void testIsExit() {
        assertFalse(new InvalidInputCommand("Some exception message").isExit());
    }

    /**
     * Test that <code>InvalidInputCommand</code> (correctly) does not change the input <code>TaskList</code>.
     */
    @Test
    public void testExecute() {
        assertEquals(1, this.tasks.getSize());
        assertEquals(this.toDo, this.tasks.getTaskByIndex(1));

        new InvalidInputCommand("Some exception message").execute(this.tasks);

        assertEquals(1, this.tasks.getSize());
        assertEquals(this.toDo, this.tasks.getTaskByIndex(1));
    }

    /**
     * Test that <code>InvalidInputCommand</code> correctly respond to the exception when
     * no valid action could be inferred from the input.
     */
    @Test
    public void testInvalidActionResponse() {
        Command command = Parser.parse("Some input without a valid action type");
        assertTrue(command instanceof InvalidInputCommand);
        String expectedResponse = "OOPS!!! I'm sorry, but I don't know what 'SOME' means :-(";
        assertEquals(expectedResponse, command.getResponse(this.tasks));
    }

    /**
     * Test that <code>InvalidInputCommand</code> correctly respond to the exception when a task
     * description is missing when required.
     */
    @Test
    public void testMissingDescriptionResponse() {
        for (String input : Arrays.asList(
                "done",
                "delete",
                "find",
                "todo",
                "deadline",
                "event"
        )) {
            Command command = Parser.parse(input);
            assertTrue(command instanceof InvalidInputCommand);
            String expectedResponse = "You need to add a description for the action '" + input.toUpperCase() + "' :-)";
            assertEquals(expectedResponse, command.getResponse(this.tasks));
        }
    }

    /**
     * Test that <code>InvalidInputCommand</code> correctly respond to the exception when, for a DONE
     * or DELETE command, the accompanying description is not an integer.
     */
    @Test
    public void testTaskNumberNotIntResponse() {
        for (String input : Arrays.asList(
                "done a",
                "delete b"
        )) {
            Command command = Parser.parse(input);
            assertTrue(command instanceof InvalidInputCommand);
            String expectedResponse = "The input that follows a 'done' action should be an integer! :D";
            assertEquals(expectedResponse, command.getResponse(this.tasks));
        }
    }

    /**
     * Test that <code>InvalidInputCommand</code> correctly respond to the exception when, for a DEADLINE
     * command, a datetime is not specified in the input.
     */
    @Test
    public void testMissingDeadlineResponse() {
        for (String input : Arrays.asList(
                "deadline CS2103 Quiz",
                "deadline CS2103 Quiz /by"
        )) {
            Command command = Parser.parse(input);
            assertTrue(command instanceof InvalidInputCommand);
            String expectedResponse = "You need to specify a deadline following a '/by' marker...";
            assertEquals(expectedResponse, command.getResponse(this.tasks));
        }
    }

    /**
     * Test that <code>InvalidInputCommand</code> correctly respond to the exception when, for an EVENT
     * command, an datetime is not specified in the input.
     */
    @Test
    public void testMissingEventTimeResponse() {
        for (String input : Arrays.asList(
                "event CS2103 Quiz",
                "event CS2103 Quiz /at"
        )) {
            Command command = Parser.parse(input);
            assertTrue(command instanceof InvalidInputCommand);
            String expectedResponse = "You need to specify an event time following an '/at' marker...";
            assertEquals(expectedResponse, command.getResponse(this.tasks));
        }
    }

    /**
     * Test that <code>InvalidInputCommand</code> correctly respond to the exception when, for a DEADLINE
     * or an EVENT command, the datetime specified in the input is not in a valid format.
     */
    @Test
    public void testInvalidDateTimeFormatResponse() {
        String inputToAddDeadline = "deadline CS2103 Quiz /by";
        String inputToAddEvent = "event CS2103 Quiz /at";

        for (String dateTimeInInvalidFormat : Arrays.asList(
                "some invalid string",
                "2020-10-35",
                "11 Feb 2021",
                "11-02-2021",
                "02-11-2021",
                "2020-02-11 10",
                "2020-02-11 10 PM",
                "2020-02-11 25:00",
                "2020-02-11 10:00 PM"
        )) {
            Command addDeadlineCommand = Parser.parse(inputToAddDeadline + dateTimeInInvalidFormat);
            assertTrue(addDeadlineCommand instanceof InvalidInputCommand);

            Command addEventCommand = Parser.parse(inputToAddEvent + dateTimeInInvalidFormat);
            assertTrue(addEventCommand instanceof InvalidInputCommand);

            String expectedResponse = "I can't recognize '" + dateTimeInInvalidFormat
                    + "' as a date. Please follow the 'YYYY-MM-DD HH:mm' format :P";

            assertEquals(expectedResponse, addDeadlineCommand.getResponse(this.tasks));
            assertEquals(expectedResponse, addEventCommand.getResponse(this.tasks));
        }
    }
}
