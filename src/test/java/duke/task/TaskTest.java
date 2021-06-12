package duke.task;

import duke.exceptions.EmptyTaskDukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void getIsTaskCompleted_incompleteTask_false() {
        try {
            Task task = new Task("task name");
            assertEquals(false, task.getIsTaskCompleted());
        } catch (EmptyTaskDukeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getIsTaskCompleted_completedTask_true() {
        try {
            Task task = new Task("task name");
            task.setDone();
            assertEquals(true, task.getIsTaskCompleted());
        } catch (EmptyTaskDukeException e) {
            e.printStackTrace();
        }
    }
}
