package mike;

import mike.task.Deadline;
import mike.task.Event;
import mike.task.ToDo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {
    @Test
    public void parseCommandToDo() {
        Command c = Parser.parseCommand("todo");
        assertEquals(c, Command.TODO);
    }

    @Test
    public void parseCommandDeadline() {
        Command c = Parser.parseCommand("deadline");
        assertEquals(c, Command.DEADLINE);
    }

    @Test
    public void parseCommandEvent() {
        Command c = Parser.parseCommand("EVENT");
        assertEquals(c, Command.EVENT);
    }

    @Test
    public void parseCommandByeTest() {
        Command c = Parser.parseCommand("bye");
        assertEquals(c, Command.BYE);
    }

    @Test
    public void parseCommandDoneTest() {
        Command c = Parser.parseCommand("done");
        assertEquals(c, Command.DONE);
    }

    @Test
    public void parseCommandListTest() {
        Command c = Parser.parseCommand("list");
        assertEquals(c, Command.LIST);
    }

    @Test
    public void parseToDoTest() {
        ToDo todo = (ToDo) Parser.parseDescription(Command.TODO, "Read Books");
        assertEquals(todo.toString(), new ToDo("Read Books").toString());
    }

    @Test
    public void parseDeadlineTest() {
        Deadline deadline = (Deadline) Parser.parseDescription(Command.DEADLINE, "testing /by 2021-01-25");
        assertEquals(deadline.toString(), new Deadline("testing ", LocalDate.parse("2021-01-25").toString()).toString());
    }
    @Test
    public void parseEventTest() {
        Event event = (Event) Parser.parseDescription(Command.EVENT, "testing /at 2021-01-25");
        assertEquals(event.toString(), new Event("testing ", LocalDate.parse("2021-01-25").toString()).toString());
    }


}