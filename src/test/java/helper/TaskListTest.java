package helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import task.Task;
import task.Todo;

class TaskListTest {

    private TaskList taskList = new TaskList();

    @Test
    void get() {
        Task task = new Todo("For testing");
        taskList.add(task);
        Task task1 = taskList.get(0);
        assertSame(task, task1);
    }

    @Test
    void add() {
        Task task = new Todo("For testing");
        taskList.add(task);
        List<Task> listOfTasks = taskList.getTaskList();
        assertTrue(listOfTasks.contains(task));
    }

    @Test
    void remove() {
        Task task = new Todo("For testing");
        taskList.add(task);
        taskList.remove(0);
        List<Task> listOfTasks = taskList.getTaskList();
        assertTrue(listOfTasks.isEmpty());
    }

    @Test
    void size() {
        Task task = new Todo("For testing");
        taskList.add(task);
        int size = taskList.size();
        assertEquals(1, size);
    }

    @Test
    void clear() {
        Task task0 = new Todo("For testing");
        Task task1 = new Todo("For testing");
        taskList.add(task0);
        taskList.add(task1);
        taskList.clear();
        List<Task> listOfTasks = taskList.getTaskList();
        assertTrue(listOfTasks.isEmpty());
    }

    @Test
    void getTaskList() {
        Task task = new Todo("For testing");
        taskList.add(task);
        List<Task> listOfTasks = taskList.getTaskList();
        assertTrue(listOfTasks.contains(task));
    }
}
