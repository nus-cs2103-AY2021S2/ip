package duke.tasks;

import duke.exceptions.DukeException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    private Task todo;
    private Task event;
    private Task deadline;
    private TaskList tasks;

    @BeforeEach
    public void init() throws DukeException {
        todo = new ToDo("todo"); //task 1
        event = new Event("event", "2021-02-02"); //task 2
        deadline = new Deadline("deadline", "2021-02-02"); //task 3
        tasks = new TaskList();
        tasks.addTask(todo);
        tasks.addTask(event);
        tasks.addTask(deadline);
    }

    @Test
    public void getTask_success() throws DukeException {
        Task task = tasks.getTask(0);
        assertEquals(task, todo);
    }

    @Test
    public void getTask_invalidTaskNum_throwsDukeException() {
        assertThrows(DukeException.class, () -> tasks.getTask(-1));
    }
}
