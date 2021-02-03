import duke.Duke;
import duke.exceptions.DukeInvalidDesException;
import duke.handler.CommandHandler;
import duke.handler.DeleteHandler;
import duke.tasks.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DukeTest {
    @Test
    public void testCheckDeleteDonePossible() {
        TaskList tasks = new TaskList();
        assertThrows(DukeInvalidDesException.class,
                () -> { Duke.checkDeleteDonePossible(new DeleteHandler(2),tasks);
                });
    }
}
