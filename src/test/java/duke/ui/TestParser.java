package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import duke.commands.AddTaskCommand;
import duke.commands.ByeCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.FindCommand;
import duke.commands.InvalidInputCommand;
import duke.commands.ListCommand;
import duke.commands.ReminderCommand;

/**
 * JUnit test for the <code>Parser</code> class in duke.ui
 */
public class TestParser {

    /**
     * Tests that the parser interprets our raw input correctly.
     */
    @Test
    public void testParser() {
        // Test that parsing valid input will return commands that carry out concrete action
        assertTrue(Parser.parse("bye") instanceof ByeCommand);
        assertTrue(Parser.parse("list") instanceof ListCommand);
        assertTrue(Parser.parse("done 1") instanceof DoneCommand);
        assertTrue(Parser.parse("delete 1") instanceof DeleteCommand);
        assertTrue(Parser.parse("find CS") instanceof FindCommand);
        assertTrue(Parser.parse("reminder 1") instanceof ReminderCommand);
        assertTrue(Parser.parse("todo CS2103 Quiz") instanceof AddTaskCommand);
        assertTrue(Parser.parse("deadline CS2103 Quiz /by 2021-02-06 23:30") instanceof AddTaskCommand);
        assertTrue(Parser.parse("deadline CS2103 Quiz /by 2021-02-06") instanceof AddTaskCommand);
        assertTrue(Parser.parse("event CS2103 Quiz /at 2021-02-06 23:30") instanceof AddTaskCommand);
        assertTrue(Parser.parse("event CS2103 Quiz /at 2021-02-06") instanceof AddTaskCommand);

        // Test that parsing invalid input would return the InvalidInputCommand, which does nothing.
        for (String input : Arrays.asList(
                "something invalid",
                "done",
                "done something",
                "delete",
                "delete something",
                "find",
                "remind",
                "remind something",
                "remind 0",
                "remind -1",
                "todo",
                "deadline",
                "deadline something",
                "deadline something /by",
                "deadline something /by invalid datetime string",
                "event",
                "event something",
                "event something /at",
                "event something /at invalid datetime string"
        )) {
            assertTrue(Parser.parse(input) instanceof InvalidInputCommand);
        }
    }

    /**
     * Tests that <code>Parser</code> converts the datetime strings in our raw input (if any) to
     * <code>LocalDateTime</code> objects correctly.
     */
    @Test
    public void testDateTimeParser() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");

        // Converting datetime strings to LocalDateTime objects
        String dateTimeString = "2021-02-06 23:30";
        LocalDateTime dateTime = Parser.convertToDateTime(dateTimeString);
        assertNotNull(dateTime);
        assertEquals(dateTimeString, dateTime.format(formatter));

        // Converting date strings to LocalDateTime objects, with time set to 00:00
        String dateString = "2021-02-06";
        LocalDateTime date = Parser.convertToDateTime(dateString);
        assertNotNull(date);
        assertEquals(dateString + " 00:00", date.format(formatter));

        // Handling invalid date or datetime strings
        String nonDateTimeString = "not a valid datetime string";
        LocalDateTime nonDateTime = Parser.convertToDateTime(nonDateTimeString);
        assertNull(nonDateTime);
    }
}
