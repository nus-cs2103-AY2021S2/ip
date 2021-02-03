import duke.exceptions.DukeException;
import duke.handler.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
