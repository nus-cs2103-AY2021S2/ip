package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class DukeTest {

    @Test
    public void dukeConstructor() {
        assertDoesNotThrow(() -> new Duke("./data/duke.txt") );
    }
}
