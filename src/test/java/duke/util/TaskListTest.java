package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    private TaskList lst;

    @Test
    public void deleteTaskTest() throws DukeException {
        lst = new TaskList();

        Task t = Todo.createTodo("test");
        lst.addTask(t);
        assertEquals(t, lst.deleteTask(0));

        assertThrows(DukeException.class, () -> lst.deleteTask(0));
        assertThrows(DukeException.class, () -> lst.deleteTask(-1));
        assertThrows(DukeException.class, () -> lst.deleteTask(100));
    }

    @Test
    public void completeTaskTest() {
        lst = new TaskList();
        assertThrows(DukeException.class, () -> lst.completeTask(0));
        assertThrows(DukeException.class, () -> lst.completeTask(-1));
        assertThrows(DukeException.class, () -> lst.completeTask(100));
    }

    @Test
    public void listOutTaskTest() throws DukeException {
        lst = new TaskList();
        lst.addTask(Todo.createTodo("a"));
        lst.addTask(Todo.createTodo("b"));

        assertEquals("1. [T][ ] a", lst.listOutTask().get(0));
        assertEquals("2. [T][ ] b", lst.listOutTask().get(1));
    }

    @Test
    public void searchTest() throws DukeInputException {
        lst = new TaskList();
        lst.addTask(Todo.createTodo("a"));
        lst.addTask(Todo.createTodo("b"));
        lst.addTask(Deadline.createDeadline("a /by 2011-01-01"));
        lst.addTask(Deadline.createDeadline("b /by 2011-02-02"));

        assertEquals("1. [T][ ] a", lst.search("a").get(0));
        assertEquals("3. [D][ ] a (by: 1 Jan)", lst.search("a").get(1));

        assertEquals("3. [D][ ] a (by: 1 Jan)", lst.search("2011-01-01").get(0));
        assertEquals("4. [D][ ] b (by: 2 Feb)", lst.search("2011-02-02").get(0));

        assertEquals(0, lst.search("test").size());
    }

}
