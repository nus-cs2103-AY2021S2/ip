package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void createDeadline_tagOnly_exceptionThrown() {
        assertThrows(DukeException.class, () -> {
            Deadline.createDeadline(new String[]{"deadline"});
        });
    }

    @Test
    void createDeadline_titleOnly_exceptionThrown() {
        assertThrows(DukeException.class, () -> {
            Deadline.createDeadline(new String[]{"deadline", "deadline title"});
        });
    }

    @Test
    void createDeadline_dateTimeOnly_exceptionThrown() {
        assertThrows(DukeException.class, () -> {
            Deadline.createDeadline(new String[]{"deadline", "/by 01/01/2001 2359"});
        });
    }

    @Test
    void createDeadline_allFields_success() throws DukeException {
        Task deadline = Deadline.createDeadline(new String[]{"deadline", "deadline title /by 01/01/2001 2359"});
        assertEquals(deadline.getFormattedStorageString(), "DEADLINE /&/ 0 /&/ deadline title /&/ 2001-01-01T23:59\n");
    }
}