package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;

class ParserTest {

    @Test
    public void parseCommandForByeTest() {
        Command c = Parser.parseCommandForSingleWord("bye");
        assertEquals(c, Command.BYE);
    }
    @Test
    public void parseCommandForDoneTest() {
        Command c = Parser.parseCommandForSingleWord("done");
        assertEquals(c, Command.DONE);
    }
    @Test
    public void parseCommandForToDoTest() {
        Command c = Parser.parseCommandForMultipleWords("todo TODO");
        assertEquals(c, Command.TODO);
    }
    @Test
    public void parseCommandForDeadlineTest() {
        Command c = Parser.parseCommandForMultipleWords("deadline DEADLINE /by 2021-02-26");
        assertEquals(c, Command.DEADLINE);
    }
    @Test
    public void parseCommandForEventTest() {
        Command c = Parser.parseCommandForMultipleWords("event EVENT /at 2021-02-20");
        assertEquals(c, Command.EVENT);
    }
    @Test
    public void parseCommandForListTest() {
        Command c = Parser.parseCommandForSingleWord("list");
        assertEquals(c, Command.LIST);
    }
    @Test
    public void parseDeadlineTest() {
        Deadline deadline = (Deadline) Parser.parseDescription(Command.DEADLINE, "nothing /by 2021-01-20");
        assertEquals(deadline.toString(), new Deadline("nothing ", LocalDate.parse("2021-01-20")).toString());
    }
    @Test
    public void parseEventTest() {
        Event event = (Event) Parser.parseDescription(Command.EVENT, "nothing /at 2021-01-20");
        assertEquals(event.toString(), new Event("nothing ", LocalDate.parse("2021-01-20")).toString());
    }
}
