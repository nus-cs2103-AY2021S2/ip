package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

/**
 * JUnit test for the <code>Storage</code> class in duke.tasks
 */
public class TestStorage {
    private final ToDo toDo;
    private final Deadline deadline;
    private final Event event;
    private final TaskList tasks;

    /**
     * Initializes some tasks and a <code>TaskList</code> instance for testing.
     */
    public TestStorage() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse("2021-02-06 23:30", formatter);

        this.toDo = new ToDo("CS2103 Quiz 1");
        this.deadline = new Deadline("BT4013 Quiz 2", dateTime);
        this.event = new Event("CS2103 Quiz 3", dateTime);

        this.deadline.markAsDone();

        this.tasks = new TaskList();
        this.tasks.addTask(this.toDo);
        this.tasks.addTask(this.deadline);
        this.tasks.addTask(this.event);
    }

    /**
     * Tests whether an empty <code>TaskList</code> is (correctly) loaded when there
     * is no text file at the specified path.
     */
    @Test
    public void testLoadEmptyTasks() {
        Storage invalidStorage = new Storage("filepath_that_does_not_exist.txt");
        TaskList emptyTaskList = invalidStorage.loadTasks();
        assertEquals(0, emptyTaskList.getSize());
    }

    /**
     * First, tests whether the tasks in a <code>TaskList</code> can be saved correctly into a specified
     * text file.
     * Then, tests whether the same tasks saved into the specified text file can be loaded correctly into
     * another <code>TaskList</code> instance.
     */
    @Test
    public void testSaveAndLoadTasks() {
        Storage storage = new Storage("test.txt");
        storage.saveTasks(this.tasks);

        TaskList loadedTasks = storage.loadTasks();

        // Check that the loaded tasks are the same as the ones that were saved.
        assertEquals(3, loadedTasks.getSize());

        ToDo firstTask = (ToDo) loadedTasks.getTaskByIndex(1);
        assertEquals(this.toDo.getDescription(), firstTask.getDescription());
        assertEquals(this.toDo.isDone(), firstTask.isDone());

        Deadline secondTask = (Deadline) tasks.getTaskByIndex(2);
        assertEquals(this.deadline.getDescription(), secondTask.getDescription());
        assertEquals(this.deadline.getByDateTimeString(), secondTask.getByDateTimeString());
        assertEquals(this.deadline.isDone(), secondTask.isDone());

        Event thirdTask = (Event) tasks.getTaskByIndex(3);
        assertEquals(this.event.getDescription(), thirdTask.getDescription());
        assertEquals(this.event.getAtDateTimeString(), thirdTask.getAtDateTimeString());
        assertEquals(this.event.isDone(), thirdTask.isDone());
    }
}
