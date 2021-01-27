import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class UiTest {
    Ui ui = new Ui();

    @Test
    void todo() throws DukeException {
        ui.todo("read book");
        assertEquals(new ToDo("read book").toString(), TaskList.tasks.get(0).toString());
    }

    @Test
    void event() throws DukeException {
        ui.event("read book", LocalDate.parse("2001-02-03"), LocalTime.parse("11:11"));
        assertEquals(new Event("read book", LocalDate.parse("2001-02-03"), LocalTime.parse("11:11")).toString()
                , TaskList.tasks.get(1).toString());
    }

    @Test
    void deadline() throws DukeException {
        ui.deadline("read book", LocalDate.parse("2001-02-03"), LocalTime.parse("11:11"));
        assertEquals(new Deadline("read book", LocalDate.parse("2001-02-03"), LocalTime.parse("11:11")).toString()
                , TaskList.tasks.get(2).toString());
    }
}
