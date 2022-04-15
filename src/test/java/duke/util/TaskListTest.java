package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    private TaskList lst;

    @Test
    public void deleteTaskTest() throws DukeException {
        lst = new TaskList();

        Task t = Todo.createTodo("test");
        lst.addTask(t);
        lst.addTask(t);
        lst.addTask(t);
        assertEquals("[[T][ ] test, [T][ ] test]", Arrays.toString(lst.deleteTask(new int[]{0, 1})));
        assertEquals("[[T][ ] test]", Arrays.toString(lst.deleteTask(new int[]{0})));

        assertThrows(DukeException.class, () -> lst.deleteTask(new int[]{0}));
        assertThrows(DukeException.class, () -> lst.deleteTask(new int[]{-1}));
        assertThrows(DukeException.class, () -> lst.deleteTask(new int[]{100}));
    }

    @Test
    public void completeTaskTest() throws DukeInputException {
        lst = new TaskList();

        Task t = Todo.createTodo("test");
        lst.addTask(t);
        assertEquals("[[T][X] test]", Arrays.toString(lst.completeTask(new int[]{0})));

        assertThrows(DukeException.class, () -> lst.completeTask(new int[]{-1}));
        assertThrows(DukeException.class, () -> lst.completeTask(new int[]{100}));
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

    @Test
    public void sortTest() throws DukeInputException {
        lst = new TaskList();
        lst.addTask(Deadline.createDeadline("c /by 2011-01-01"));
        lst.addTask(Deadline.createDeadline("a /by 2011-01-01"));
        lst.addTask(Event.createEvent("b /at 2011-02-02"));
        lst.addTask(Todo.createTodo("a"));
        lst.addTask(Todo.createTodo("b"));
        lst.sort();

        assertEquals("[T][ ] a", lst.getList().get(0).toString());
        assertEquals("[T][ ] b", lst.getList().get(1).toString());
        assertEquals("[D][ ] a (by: 1 Jan)", lst.getList().get(2).toString());
        assertEquals("[D][ ] c (by: 1 Jan)", lst.getList().get(3).toString());
        assertEquals("[E][ ] b (at: 2 Feb)", lst.getList().get(4).toString());
    }
}
