import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import bob.BobException;
import bob.processor.Parser;

public class ParserTest {

    @Test
    public void parseName_validName() throws BobException {
        Parser parser = new Parser();
        String expected = "eat and sleep";
        String parsedTodoName = parser.parseName("todo eat and sleep");
        assertEquals(expected, parsedTodoName);

        String parsedEventName = parser.parseName("event eat and sleep /at: 2pm");
        assertEquals(expected, parsedEventName);

        String parsedDeadlineName = parser.parseName("deadline eat and sleep /by: 9pm");
        assertEquals(expected, parsedDeadlineName);
    }

    @Test
    public void parseDateTime_eventDateTime_validDateTime() throws BobException {
        Parser parser = new Parser();

        String userInput = "event eat and sleep /at: 2021-11-02 0200";
        String taskType = "event";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a");
        String actualParsedDateTime = parser.parseDateTime(userInput, taskType).format(dateFormatter);
        String expectedParsedDateTime = "02 Nov 2021 2:00 AM";
        assertEquals(actualParsedDateTime, expectedParsedDateTime);
    }

    @Test
    public void parseDateTime_deadlineDateTime_validDateTime() throws BobException {
        Parser parser = new Parser();

        String userInput = "deadline eat and sleep /by: 2021-11-02 2359";
        String taskType = "deadline";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a");
        String actualParsedDateTime = parser.parseDateTime(userInput, taskType).format(dateFormatter);
        String expectedParsedDateTime = "02 Nov 2021 11:59 PM";
        assertEquals(actualParsedDateTime, expectedParsedDateTime);
    }
    @Test
    public void parseDateTime_remindDateTime_validDateTime() throws BobException {
        Parser parser = new Parser();

        String userInput = "remind 1 /on: 2021-04-13 1400";
        String taskType = "remind";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a");
        String actualParsedDateTime = parser.parseDateTime(userInput, taskType).format(dateFormatter);
        String expectedParsedDateTime = "13 Apr 2021 2:00 PM";
        assertEquals(actualParsedDateTime, expectedParsedDateTime);
    }


}
