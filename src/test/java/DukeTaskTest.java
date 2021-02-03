import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTaskTest {
    @Test
    public void testTask() {
        Task[] tasks = new Task[] {new Todo("a"),
            new Deadline("b", LocalDate.parse("2020-01-01")),
            new Event("c", LocalDate.parse("2020-01-01"))};
        String[] strTasksNotDone = new String[] {"[T][ ] a", "[D][ ] b (by: Jan 01 2020)",
            "[E][ ] c (at: Jan 01 2020)"};
        String[] strTasksDone = new String[] {"[T][X] a", "[D][X] b (by: Jan 01 2020)",
            "[E][X] c (at: Jan 01 2020)"};
        for (int i = 0; i < 3; i++) {
            assertEquals(tasks[i].toString(), strTasksNotDone[i]);
        }
        for (Task task : tasks) {
            task.done();
        }
        for (int i = 0; i < 3; i++) {
            assertEquals(tasks[i].toString(), strTasksDone[i]);
        }
    }
}
