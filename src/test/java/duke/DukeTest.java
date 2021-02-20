package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class DukeTest {
    @Test
    public void greetTest() {
        Duke duke = new Duke();
        String greetingMessage = "Hello! I'm Spongebob!\n";
        greetingMessage += "What can I do for you?";
        assertEquals(greetingMessage, duke.greet());
    }

    @Test
    public void exitTest() {
        Duke duke = new Duke();
        String greetingMessage = "Bye. Hope to see you again soon!";
        assertEquals(greetingMessage, duke.exit());
    }

    @Test
    public void testGetResponse() {
        Duke duke = new Duke();
        String dukeMessage = duke.getResponse("list 2");
        assertEquals("Invalid input for list command", dukeMessage);
    }

}
