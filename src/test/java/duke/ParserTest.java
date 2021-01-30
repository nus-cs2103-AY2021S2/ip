package duke;

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
        Command c = Parser.parseCommand("done 1").getCommand();
        assertEquals(c, Command.DONE);
    }
    @Test
    public void parseCommandForToDoTest() {
        DukeCommand c = Parser.parseCommand("todo todo1");
        assertEquals(c.getCommand(), Command.TODO);
        assertEquals(c.getDetails(), "todo1");
        assertEquals(Parser.parseRemainder(c.getCommand(), c.getDetails()),
                new Todo("todo1"));
    }
    @Test
    public void parseCommandForDeadlineTest() {
        DukeCommand c = Parser.parseCommand("deadline deadline1 /by 2020-04-13");
        assertEquals(c.getCommand(), Command.DEADLINE);
        assertEquals(c.getDetails(), "deadline1 /by 2020-04-13");
        assertEquals(Parser.parseRemainder(c.getCommand(), c.getDetails()),
                new Deadline("deadline1", LocalDate.parse("2020-04-13")));
    }
    @Test
    public void parseCommandForEVENTTest() {
        DukeCommand c = Parser.parseCommand("event event1 /at 2020-04-13 2-4pm");
        assertEquals(c.getCommand(), Command.EVENT);
        assertEquals(c.getDetails(), "event1 /at 2020-04-13 2-4pm");
        assertEquals(Parser.parseRemainder(c.getCommand(), c.getDetails()),
                new Event("event1", LocalDate.parse("2020-04-13"), "2-4pm"));
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
