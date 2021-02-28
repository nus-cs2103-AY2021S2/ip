package ronald.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ronald.RonaldException;
import org.junit.jupiter.api.Test;

class DeadlineTest {

    @Test
    void createDeadline_tagOnly_exceptionThrown() {
        assertThrows(RonaldException.class, () -> {
            Deadline.createDeadline(new String[]{"deadline"});
        });
    }

    @Test
    void createDeadline_titleOnly_exceptionThrown() {
        assertThrows(RonaldException.class, () -> {
            Deadline.createDeadline(new String[]{"deadline", "deadline title"});
        });
    }

    @Test
    void createDeadline_dateTimeOnly_exceptionThrown() {
        assertThrows(RonaldException.class, () -> {
            Deadline.createDeadline(new String[]{"deadline", "/by 01/01/2001 2359"});
        });
    }

    @Test
    void createDeadline_allFields_success() throws RonaldException {
        Task deadline = Deadline.createDeadline(new String[]{"deadline", "deadline title /by 01/01/2001 2359"});
        assertEquals(deadline.getFormattedStorageString(), "DEADLINE /&/ 0 /&/ deadline title /&/ 2001-01-01T23:59\n");
    }
}
