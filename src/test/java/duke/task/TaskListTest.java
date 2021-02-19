package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void markTaskAsDone_correctIndex_success() throws Exception {
        Task testTask = new Task("testing");
        TaskList testTaskList = new TaskList();
        testTaskList.add(testTask);
        testTaskList.markTaskAsDone("1");

        assertEquals(testTask.getIsDone(), true);
    }

    @Test
    public void markTaskAsDone_negativeIndex_throwException() throws Exception {
        try {
            Task testTask = new Task("testing");
            TaskList testTaskList = new TaskList();
            testTaskList.add(testTask);
            testTaskList.markTaskAsDone("-1");

            assertEquals(testTask.getIsDone(), true);
            fail();
        } catch (Exception e) {
            assertEquals("Please enter an appropriate index.", e.getMessage());
        }

    }

    @Test
    public void markTaskAsDone_outOfBoundsIndex_throwException() throws Exception {
        try {
            Task testTask = new Task("testing");
            TaskList testTaskList = new TaskList();
            testTaskList.add(testTask);
            testTaskList.markTaskAsDone("3");

            assertEquals(testTask.getIsDone(), true);
            fail();
        } catch (Exception e) {
            assertEquals("Please enter an appropriate index.", e.getMessage());
        }

    }
}
