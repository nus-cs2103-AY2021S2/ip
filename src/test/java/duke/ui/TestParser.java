package duke.ui;

import duke.commands.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Arrays;

public class TestParser {

    @Test
    public void testParser() {
        // Test that parsing valid input will return commands that carry out concrete action
        assertTrue(Parser.parse("bye") instanceof ByeCommand);
        assertTrue(Parser.parse("list") instanceof ListCommand);
        assertTrue(Parser.parse("done 1") instanceof DoneCommand);
        assertTrue(Parser.parse("delete 1") instanceof DeleteCommand);
        assertTrue(Parser.parse("todo CS2103 Quiz") instanceof AddToDoCommand);
        assertTrue(Parser.parse("deadline CS2103 Quiz /by 2021-02-06 23:30") instanceof AddDeadlineCommand);
        assertTrue(Parser.parse("deadline CS2103 Quiz /by 2021-02-06") instanceof AddDeadlineCommand);
        assertTrue(Parser.parse("event CS2103 Quiz /at 2021-02-06 23:30") instanceof AddEventCommand);
        assertTrue(Parser.parse("event CS2103 Quiz /at 2021-02-06") instanceof AddEventCommand);

        // Test that parsing invalid input would return the DoNothingCommand, which does nothing (obviously)
        for (String input : Arrays.asList(
                "something invalid",
                "done",
                "done something",
                "delete",
                "delete something",
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
            assertTrue(Parser.parse(input) instanceof DoNothingCommand);
        }
    }

    @Test
    public void testDateTimeParser() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");

        String dateTimeString = "2021-02-06 23:30";
        LocalDateTime dateTime = Parser.convertToDateTime(dateTimeString);
        assertNotNull(dateTime);
        assertEquals(dateTimeString, dateTime.format(formatter));

        String dateString = "2021-02-06";
        LocalDateTime date = Parser.convertToDateTime(dateString);
        assertNotNull(date);
        assertEquals(dateString + " 00:00", date.format(formatter));

        String nonDateTimeString = "not a valid datetime string";
        LocalDateTime nonDateTime = Parser.convertToDateTime(nonDateTimeString);
        assertNull(nonDateTime);
    }
}
