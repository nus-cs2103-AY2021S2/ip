package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.helper.HelperFunctions;

/**
 * JUnit test for the <code>Storage</code> class in duke.tasks
 */
public class TestStorage {
    private final TaskList tasks;

    /**
     * Initializes some tasks and a <code>TaskList</code> instance for testing.
     */
    public TestStorage() {
        this.tasks = new TaskList();
        this.tasks.addTask(new ToDo("CS2103 Quiz"));
        this.tasks.addTask(new Deadline("CS2103 Quiz", LocalDateTime.now()));
        this.tasks.addTask(new Event("CS2103 Quiz", LocalDateTime.now()));
    }

    /**
     * Tests whether an empty <code>TaskList</code> is (correctly) loaded when there
     * is no text file at the specified path.
     */
    @Test
    public void testLoadEmptyTasks() {
        Storage invalidStorage = new Storage("file_that_does_not_exist.txt");
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
        assertTrue(HelperFunctions.taskListsAreEqual(this.tasks, loadedTasks));
    }
}
