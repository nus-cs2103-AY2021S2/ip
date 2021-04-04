import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;
import duke.handler.ByeHandler;
import duke.handler.CommandHandler;
import duke.handler.DeadlineHandler;
import duke.handler.DeleteHandler;
import duke.handler.DoneHandler;
import duke.handler.EventHandler;
import duke.handler.ListHandler;
import duke.handler.Parser;
import duke.handler.TodoHandler;


public class ParserTest {
    @Test
    public void testParseFromInput() throws DukeException {
        CommandHandler cmdHandler = Parser.parseFromInput("todo testing");
        assertEquals(new TodoHandler("testing"), cmdHandler);

        cmdHandler = Parser.parseFromInput("deadline testing /by 01.Feb.2021 23:59");
        assertEquals(new DeadlineHandler("testing ", LocalDateTime.parse("2021-02-01T23:59:00")),
                cmdHandler);

        cmdHandler = Parser.parseFromInput("event testing /at 01.Feb.2021 23:59");
        assertEquals(new EventHandler("testing ", LocalDateTime.parse("2021-02-01T23:59:00")),
                cmdHandler);

        cmdHandler = Parser.parseFromInput("list");
        assertEquals(new ListHandler(), cmdHandler);

        cmdHandler = Parser.parseFromInput("done 2");
        assertEquals(new DoneHandler(2), cmdHandler);

        cmdHandler = Parser.parseFromInput("delete 2");
        assertEquals(new DeleteHandler(2), cmdHandler);

        cmdHandler = Parser.parseFromInput("bye");
        assertEquals(new ByeHandler(), cmdHandler);
    }

}
