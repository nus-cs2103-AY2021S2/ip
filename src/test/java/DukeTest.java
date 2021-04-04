import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.Duke;
import duke.exceptions.DukeInvalidDesException;
import duke.handler.DeleteHandler;
import duke.tasks.TaskList;


public class DukeTest {
    @Test
    public void testCheckDeleteDonePossible() {
        TaskList tasks = new TaskList();
        assertThrows(DukeInvalidDesException.class, (
        ) -> {
            Duke.checkDeleteDonePossible(new DeleteHandler(2), tasks);
        });
    }
}
