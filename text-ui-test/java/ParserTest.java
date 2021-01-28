import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parseCommand_validCommand () {
        Parser parser = new Parser();
        Command todo = parser.parseCommand("todo eat and sleep");
        assertEquals(todo, Command.TODO);

        Command event = parser.parseCommand("event eat and sleep /at: 2pm");
        assertEquals(event, Command.EVENT);

        Command deadline = parser.parseCommand("deadline eat and sleep /by: 9pm");
        assertEquals(deadline, Command.DEADLINE);
    }

    @Test
    public void parseEvent_validCommand() throws DukeException {
        Parser parser = new Parser();
        String userInput = "event eat and sleep /at: 2020-02-02 2pm";
        int timeIndex = userInput.indexOf("/at:");
        Event event = parser.parseEvent(userInput, timeIndex);
        String expected = "[E][ ] eat and sleep (at: FEBRUARY 2 2020 2pm)";
        assertEquals(event.toString(), expected);
    }

}
