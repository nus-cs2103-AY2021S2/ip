package duke.util;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.List;

public class TaskListTest {
    
    TaskList lst = new TaskList();

    @Test
    public void deleteTaskTest() throws DukeException {

        Task t = Todo.createTodo("test");
        lst.addTask(t);
        assertEquals(t, lst.deleteTask(0));

        assertThrows(DukeException.class, () -> lst.deleteTask(0));
        assertThrows(DukeException.class, () -> lst.deleteTask(-1));
        assertThrows(DukeException.class, () -> lst.deleteTask(100));
    }

    @Test
    public void completeTaskTest() {
        assertThrows(DukeException.class, () -> lst.completeTask(0));
        assertThrows(DukeException.class, () -> lst.completeTask(-1));
        assertThrows(DukeException.class, () -> lst.completeTask(100));
    }

    @Test
    public void listOutTaskTest() throws DukeException {
        lst.addTask(Todo.createTodo("a"));
        lst.addTask(Todo.createTodo("b"));
        List<String> expected = List.of("1. [T][ ] a", "2. [T][ ] b");

        assertEquals(expected.get(0), lst.listOutTask().get(0));
        assertEquals(expected.get(1), lst.listOutTask().get(1));
    }

}
