package java.duke;

import main.java.duke.Command;
import main.java.duke.Deadline;
import main.java.duke.DukeCommand;
import main.java.duke.Event;
import main.java.duke.Parser;
import main.java.duke.Task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parseCommandForByeTest() {
        Command c = Parser.parseCommand("bye").getCommand();
        assertEquals(c, Command.BYE);
    }

    @Test
    public void parseCommandForDoneTest() {
        Command c = Parser.parseCommand("done").getCommand();
        assertEquals(c, Command.DONE);
    }
    @Test
    public void parseCommandForToDoTest() {
        Command c = Parser.parseCommand("todo").getCommand();
        assertEquals(c, Command.TODO);
    }
    @Test
    public void parseCommandForDeadlineTest() {
        Command c = Parser.parseCommand("deadline").getCommand();
        assertEquals(c, Command.DEADLINE);
    }
    @Test
    public void parseCommandForEVENTTest() {
        Command c = Parser.parseCommand("event").getCommand();
        assertEquals(c, Command.EVENT);
    }
    @Test
    public void parseCommandForListTest() {
        Command c = Parser.parseCommand("list").getCommand();
        assertEquals(c, Command.LIST);
    }
    @Test
    public void parseDeadlineTest() {
        DukeCommand dukeCommand = Parser.parseCommand("deadline deadline1 /by 2021-01-20");
        Task deadline = Parser.parseRemainder(dukeCommand.getCommand(), dukeCommand.getDetails());
        assertEquals(deadline.toString(), new Deadline("deadline1", LocalDate.parse("2021-01-20")).toString());
    }
    @Test
    public void parseEventTest() {
        DukeCommand dukeCommand = Parser.parseCommand("event event1 /at 2021-01-20 2-4pm");
        Task event = Parser.parseRemainder(dukeCommand.getCommand(), dukeCommand.getDetails());
        assertEquals(event.toString(), new Event("event1", LocalDate.parse("2021-01-20"), "2-4pm").toString());
    }


}
