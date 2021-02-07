package duke.task;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListTest {

    private static final Task task1 = new Task("first task");
    private static final Task task2 = new Task("second task");
    private static final Task task3 = new Task("third task");

    @Test
    void testIsEmpty_emptyTaskList_returnTrue() {
        TaskList tasks = new TaskList();
        assertTrue(tasks.isEmpty());
    }

    @Test
    void testIsEmpty_nonEmptyTaskList_returnFalse() {
        TaskList tasks = new TaskList();
        tasks.addTask(task1);
        assertFalse(tasks.isEmpty());
    }

    @Test
    void testGetAllTasks_emptyTaskList_emptyList() {
        TaskList tasks = new TaskList();
        assertEquals(new ArrayList<Task>(), tasks.getAllTasks());
    }

    @Test
    void testGetAllTasks_nonEmptyTaskList_listWithAddedTask() {
        TaskList tasks = new TaskList();
        tasks.addTask(task1);
        tasks.addTask(task2);
        assertEquals(Arrays.asList(task1, task2), tasks.getAllTasks());
    }

    @Test
    void testGetTaskCount_emptyTaskList_returnZero() {
        TaskList tasks = new TaskList();
        assertEquals(0, tasks.getTaskCount());
    }

    @Test
    void testGetTaskCount_nonEmptyTaskList_returnTaskCount() {
        TaskList tasks = new TaskList();
        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.addTask(task3);
        assertEquals(3, tasks.getTaskCount());
    }

    @Test
    void testDeleteTask() {
        TaskList tasks = new TaskList();
        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.addTask(task3);
        tasks.deleteTask(1);
        assertEquals(tasks.getAllTasks(), Arrays.asList(task1, task3));
    }

    @Test
    void testGetTask() {
        TaskList tasks = new TaskList();
        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.addTask(task3);
        assertEquals(tasks.getTask(2), task3);
    }
}
