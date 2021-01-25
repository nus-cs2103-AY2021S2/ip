package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void addTask_allTypesOfTask_success() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        Task todo = new Todo("todo");
        Task deadline = new Deadline("deadline", LocalDateTime.now());
        Task event = new Event("event", LocalDateTime.now());
        TaskList.addTask(todo, tasks);
        assertEquals(tasks.size(), 1);
        TaskList.addTask(deadline, tasks);
        assertEquals(tasks.size(), 2);
        TaskList.addTask(event, tasks);
        assertEquals(tasks.size(), 3);
    }

    @Test
    void markDone_allTypesOfTask_success() throws DukeException {
        Task todo = new Todo("todo");
        Task deadline = new Deadline("deadline", LocalDateTime.now());
        Task event = new Event("event", LocalDateTime.now());

        TaskList.markDone(todo);
        TaskList.markDone(deadline);
        TaskList.markDone(event);

        assertTrue(todo.isDone, "todo marked done");
        assertTrue(deadline.isDone, "deadline marked done");
        assertTrue(event.isDone, "event marked done");
    }

    @Test
    void deleteTask_validId_success() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        Task todo = new Todo("todo");
        Task deadline = new Deadline("deadline", LocalDateTime.now());
        Task event = new Event("event", LocalDateTime.now());

        tasks.add(todo);
        tasks.add(deadline);
        tasks.add(event);

        assertEquals(tasks.size(), 3);
        assertEquals(todo, TaskList.deleteTask(0, tasks));
        assertEquals(deadline, TaskList.deleteTask(0, tasks));
        assertEquals(event, TaskList.deleteTask(0, tasks));
        assertEquals(tasks.size(), 0);
    }
}