package duke;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import utility.Parser;

public class DukeExceptionTest {

    @ParameterizedTest
    @ValueSource(strings = {"delete", "done", "todo", "deadline", "event", "find"})
    public void dukeException_emptyDeleteCommand_caught(String input) {
        Exception ex = assertThrows(DukeException.class, () -> {
            Parser.parseCommand(input);
        });

        switch (input) {
        case "delete":
            assertEquals(ex.getMessage(), "delete is missing 1 argument");
            break;
        case "list":
            assertEquals(ex.getMessage(), "list is missing 1 argument");
            break;
        case "done":
            assertEquals(ex.getMessage(), "done is missing 1 argument");
            break;
        case "todo":
            assertEquals(ex.getMessage(), "todo is missing 1 argument");
            break;
        case "deadline":
            assertEquals(ex.getMessage(), "Missing arguments for deadline");
            break;
        case "event":
            assertEquals(ex.getMessage(), "Missing arguments for event");
            break;
        case "find":
            assertEquals(ex.getMessage(), "Missing keyword for find");
            break;
        }

    }
}
