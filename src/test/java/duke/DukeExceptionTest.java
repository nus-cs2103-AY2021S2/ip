package duke;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DukeExceptionTest {
    @ParameterizedTest
    @ValueSource(strings = {"delete", "list", "done", "todo", "deadline", "event", "find"})
    public void dukeException_emptyDeleteCommand_caught(String input) {
        Exception ex = assertThrows(DukeException.class, () -> {
            Parser.parseCommand(input);
        });

        switch (input) {
        case "delete" -> assertEquals(ex.getMessage(), "delete is missing 1 argument");
        case "list" -> assertEquals(ex.getMessage(), "list is missing 1 argument");
        case "done" -> assertEquals(ex.getMessage(), "done is missing 1 argument");
        case "todo" -> assertEquals(ex.getMessage(), "todo is missing 1 argument");
        case "deadline" -> assertEquals(ex.getMessage(), "Missing arguments for deadline");
        case "event" -> assertEquals(ex.getMessage(), "Missing arguments for event");
        case "find" -> assertEquals(ex.getMessage(), "Missing keyword for find");
        }

    }
}
