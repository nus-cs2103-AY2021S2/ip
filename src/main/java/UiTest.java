import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * A class that conducts JUnit test on the methods from Ui class.
 */
public class UiTest {
    Ui ui = new Ui();

    /**
     * A test for the todo() method in Ui class.
     */
    @Test
    void todo() throws DukeException {
        ui.todo("read book");
        assertEquals(new ToDo("read book").toString(), TaskList.tasks.get(0).toString());
    }

    /**
     * A test for the event() method in Ui class.
     */
    @Test
    void event() throws DukeException {
        ui.event("read book", LocalDate.parse("2001-02-03"), LocalTime.parse("11:11"));
        assertEquals(new Event("read book", LocalDate.parse("2001-02-03"), LocalTime.parse("11:11")).toString()
                , TaskList.tasks.get(1).toString());
    }

    /**
     * A test for the deadline() method in Ui class.
     */
    @Test
    void deadline() throws DukeException {
        ui.deadline("read book", LocalDate.parse("2001-02-03"), LocalTime.parse("11:11"));
        assertEquals(new Deadline("read book", LocalDate.parse("2001-02-03"), LocalTime.parse("11:11")).toString()
                , TaskList.tasks.get(2).toString());
    }
}
