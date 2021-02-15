import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.exceptions.DukeExceptionIllegalArgument;
import duke.parser.UserInputTokenSet;
import duke.tasks.Event;

public class EventTest {

    private UserInputTokenSet tokenSet;

    @BeforeEach
    void init() {
        tokenSet = new UserInputTokenSet();
    }

    @Test
    void parse_validInput_notDone() throws DukeExceptionIllegalArgument {
        tokenSet.set("/text", "event name");
        tokenSet.set("at", "2020-09-08");
        Event event = Event.parse(tokenSet);
        assertEquals("event name", event.getDescription());
        assertFalse(event.getDone());
    }

    @Test
    void parse_validInput_done() throws DukeExceptionIllegalArgument {
        tokenSet.set("/text", "event name2");
        tokenSet.set("at", "2020-09-28");
        tokenSet.set("done", "");
        Event event = Event.parse(tokenSet);
        assertEquals("event name2", event.getDescription());
        assertTrue(event.getDone());
    }
}
