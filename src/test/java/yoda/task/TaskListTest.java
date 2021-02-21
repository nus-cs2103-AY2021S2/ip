package yoda.task;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;


class TaskListTest {
    private TaskList tasks = new TaskList(new ArrayList<>());

    @Test
    void testAddTask() throws InvalidTaskListIndexException {
        ToDo todo = new ToDo("have a break");
        tasks.addTask(todo);
        assertEquals(todo, tasks.accessTask(1));
    }

    @Test
    void testGetTaskListSize() {
        ToDo rest = new ToDo("have a break");
        ToDo coffee = new ToDo("drink coffee");
        ToDo corrections = new ToDo("make corrections");
        tasks.addTask(rest);
        tasks.addTask(coffee);
        tasks.addTask(corrections);
        assertEquals(3, tasks.getTaskListSize());
    }


    @Test
    void testMarkTaskAsDone() throws InvalidTaskListIndexException {
        ToDo rest = new ToDo("have a break");
        ToDo coffee = new ToDo("drink coffee");
        ToDo corrections = new ToDo("make corrections");
        tasks.addTask(rest);
        tasks.addTask(coffee);
        tasks.addTask(corrections);
        tasks.markTaskAsDone(2, 3);
        assertTrue(tasks.accessTask(2).isDone);
        assertTrue(tasks.accessTask(3).isDone);
    }

    @Test
    void testMarkAllTasksAsDone() throws InvalidTaskListIndexException {
        ToDo rest = new ToDo("have a break");
        ToDo coffee = new ToDo("drink coffee");
        tasks.addTask(rest);
        tasks.addTask(coffee);
        tasks.markAllTasksAsDone();
        assertTrue(tasks.accessTask(1).isDone);
        assertTrue(tasks.accessTask(2).isDone);
    }

    @Test
    void testTaskDeletion() throws InvalidTaskListIndexException {
        ToDo rest = new ToDo("have a break");
        ToDo coffee = new ToDo("drink coffee");
        ToDo corrections = new ToDo("make corrections");
        tasks.addTask(rest);
        tasks.addTask(coffee);
        tasks.addTask(corrections);
        tasks.markTaskToBeDeleted(1, 3);
        tasks.deleteMarkedTasks();
        assertEquals(coffee, tasks.accessTask(1));
    }

    @Test
    void testDeletingAllTasks() {
        ToDo rest = new ToDo("have a break");
        ToDo coffee = new ToDo("drink coffee");
        ToDo corrections = new ToDo("make corrections");
        tasks.addTask(rest);
        tasks.addTask(coffee);
        tasks.addTask(corrections);
        tasks.deleteAllTasks();
        assertEquals(0, tasks.getTaskListSize());
    }

    @Test
    void testGetNumberOfUnfinishedTasks() {
        ToDo rest = new ToDo("have a break");
        ToDo coffee = new ToDo("drink coffee");
        ToDo corrections = new ToDo("make corrections");
        ToDo read = new ToDo("read a book");
        tasks.addTask(rest);
        tasks.addTask(coffee);
        tasks.addTask(corrections);
        tasks.addTask(read);
        tasks.markTaskAsDone(1, 3, 4);
        assertEquals(1, tasks.getNumberOfUnfinishedTasks());
    }

    @Test
    void testFilterByTask() {
        Event event = new Event("attend this", "2021-09-05 1500");
        Event otherEvent = new Event("attend that", "2021-09-05 1500");
        Deadline deadline = new Deadline("complete that", "2021-01-01 1900");
        ToDo coffee = new ToDo("drink coffee");
        ToDo corrections = new ToDo("make corrections");
        ToDo read = new ToDo("read a book");
        tasks.addTask(event);
        tasks.addTask(otherEvent);
        tasks.addTask(deadline);
        tasks.addTask(coffee);
        tasks.addTask(corrections);
        tasks.addTask(read);
        TaskList toDoList = tasks.filterByTask("ToDo");
        TaskList eventList = tasks.filterByTask("Event");
        TaskList deadlineList = tasks.filterByTask("Deadline");
        TaskList filteredList = tasks.filterByTask("that");
        assertAll("Checking if filter works:", ()
                -> assertEquals(3, toDoList.getTaskListSize()), ()
                -> assertEquals(2, eventList.getTaskListSize()), ()
                -> assertEquals(1, deadlineList.getTaskListSize()), ()
                -> assertEquals(2, filteredList.getTaskListSize())
        );
    }

    @Test
    void testToString() {
        Event event = new Event("attend this", "2021-09-05 1500");
        ToDo read = new ToDo("read a book");
        tasks.addTask(event);
        tasks.addTask(read);
        String expectedString = "1.[E][ ] attend this ---> at Sep 5 2021 1500\n"
                              + "2.[T][ ] read a book";
        assertEquals(expectedString, tasks.toString());
    }
}
