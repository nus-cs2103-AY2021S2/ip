package task;

import org.junit.jupiter.api.Test;
import project.task.TaskList;
import project.task.Todo;

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

        assertEquals(true, testTaskList.hasTasks());
        assertEquals(1, testTaskList.getTotalNumberOfTasks());
        assertEquals(testTask.toString(), testTaskList.getTask(1).toString());
    }

    @Test
    public void markTaskAsDoneTest_validIndex_success() {
        Todo testTask = new Todo("test");
        testTaskList.addTask(testTask);
        testTaskList.markTaskAsDone(1);

        assertEquals(0, testTaskList.getTotalNumberOfTasksUndone());
        assertEquals(testTaskList.getTask(1).convertNotDoneStatusToOne(), 0);
    }
}
