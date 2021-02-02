import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void deadline_wrongDateFormat_dukeExceptionThrown() {
        assertThrows(DukeException.class, () -> new Deadline("return book", false, ""));
        assertThrows(DukeException.class, () -> new Deadline("return book", true, "9 Sep 2019"));
        assertThrows(DukeException.class, () -> new Deadline("return book", true, "09-09-2019"));
        assertThrows(DukeException.class, () -> new Deadline("return book", false, "2019/09/09"));
    }

    @Test
    public void testSaveTask() throws DukeException {
        assertEquals("D | 1 | return book | 2019-09-09",
                new Deadline("return book", true, "2019-09-09").saveTask());
        assertEquals("D | 0 | return book | 2019-09-09",
                new Deadline("return book", false, "2019-09-09").saveTask());
    }

    @Test
    public void testStringConversion() throws DukeException {
        assertEquals("[D][\u2713] return book (By: 9 Sep 2019)",
                new Deadline("return book", true, "2019-09-09").toString());
        assertEquals("[D][ ] return book (By: 9 Sep 2019)",
                new Deadline("return book", false, "2019-09-09").toString());
    }
}
