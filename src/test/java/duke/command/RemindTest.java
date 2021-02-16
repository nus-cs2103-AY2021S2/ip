package duke.command;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;



public class RemindTest {
    @Test
    public void remindIsUpcomingTest() {
        Deadline dummyDeadline = new Deadline("buy more stonks",
                LocalDateTime.now().plusDays(2));
        RemindCommand dummyRemindCommand = new RemindCommand();
        assertEquals(dummyRemindCommand.isUpcoming(dummyDeadline), true);
    }
}
