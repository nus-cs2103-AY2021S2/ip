package yoda.task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    TaskList tasks = new TaskList(new ArrayList<>());

    @Test
    void testAddTask() {
        ToDo todo = new ToDo("have a break");
        tasks.addTask(todo);
        assertEquals(todo, tasks.accessTask(0));
    }

    @Test
    void testMarkTaskAsDone() {
        ToDo todo = new ToDo("have a break");
        tasks.addTask(todo);
        tasks.markTaskAsDone(0);
        assertEquals(true, tasks.accessTask(0).isDone);
    }

    @Test
    void deleteTask() {
        ToDo todo = new ToDo("have a break");
        tasks.addTask(todo);
        tasks.deleteTask(0);
        assertEquals(0, tasks.length());
    }
}