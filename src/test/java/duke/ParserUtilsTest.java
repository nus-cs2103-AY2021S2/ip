package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class ParserUtilsTest {
    @Test
    public void testParseDateTimeBySlash() throws DukeException {
        LocalDateTime dateTime = ParserUtils.parseDateTime("1/12/1212", "Invalid Date");
        assertEquals(LocalDateTime.of(1212, 12, 1, 0, 0), dateTime);
    }

    @Test
    public void testParseInvalidFormatDate() {
        String errorMessage = "Invalid Date";

        Exception exception = assertThrows(DukeException.class, () -> {
            ParserUtils.parseDateTime("12/15/1212", errorMessage);
        });

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    public void testGetNoArgs() {
        String line = "command";
        String errorMessage = "No arguments given";

        Exception exception = assertThrows(DukeException.class, () -> {
            ParserUtils.getCommandArgs(line, errorMessage);
        });

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    public void testValidArgs() throws DukeException {
        String line = "command a very long argument";
        String errorMessage = "No arguments given";

        String[] args = ParserUtils.getCommandArgs(line, errorMessage);

        assertEquals("a very long argument", args[1]);
    }

}
