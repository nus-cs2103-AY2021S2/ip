package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.DukeException;

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
    void deleteTask_validId_success() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        Task todo = new Todo("todo");
        Task deadline = new Deadline("deadline", LocalDateTime.now());
        Task event = new Event("event", LocalDateTime.now());

        tasks.add(todo);
        tasks.add(deadline);
        tasks.add(event);

        assertEquals(tasks.size(), 3);
        assertTrue(TaskList.deleteTask(todo, tasks));
        assertTrue(TaskList.deleteTask(deadline, tasks));
        assertTrue(TaskList.deleteTask(event, tasks));
        assertEquals(tasks.size(), 0);
    }
}
