import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeTest {

    @Test
    public void parseInputEchoToOutput() {
        assertEquals("list", Duke.parseInput("list"));
        assertEquals("blah", Duke.parseInput("blah"));
    }

    @Test
    public void parseInputByeOutputFarewell() {
        assertEquals("Bye. Hope to see you again soon!", Duke.parseInput("bye"));
    }
}