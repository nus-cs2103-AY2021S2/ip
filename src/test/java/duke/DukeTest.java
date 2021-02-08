package duke;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import javafx.util.Pair;

public class DukeTest {

    @Test
    public void constructor_filePathProvided_noExceptionThrown() {
        assertDoesNotThrow(() -> new Duke("bin", "test", "duke"));
    }

    @Test
    public void getResponse_emptyCommand_noActionStatusCode() {
        Pair<DukeStatusCode, String> actual = new Duke("bin", "test", "duke").getResponse("");
        assertEquals(DukeStatusCode.NO_ACTION, actual.getKey());
        assertEquals("", actual.getValue());
    }
}
