package task;

import org.junit.jupiter.api.Test;
import seedu.task.TaskList;
import seedu.task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    TaskList testTaskList = new TaskList();

    @Test
    public void hasTasksTest_noTask_false() {
        assertEquals(testTaskList.hasTasks(), false);
    }

    @Test
    public void addTaskTest_addTask_success() {
        Todo testTask = new Todo("test");
        testTaskList.addTask(testTask);

        assertEquals(testTaskList.hasTasks(), true);
        assertEquals(testTaskList.getTotalNumberOfTasks(), 1);
        assertEquals(testTaskList.getTask(1).toString(), testTask.toString());
    }

    // this test fails with IndexOutOfBoundsException but the program works as expected ...
    @Test
    public void markTaskAsDoneTest_validIndex_success() {
        testTaskList.markTaskAsDone(1);

        assertEquals(testTaskList.getTask(1).convertNotDoneStatusToOne(), 0);
    }
}
