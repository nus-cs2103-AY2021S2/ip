package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TestStorage {

    /**
     * Test whether an empty <code>TaskList</code> is (correctly) loaded when there
     * is no text file at the specified path.
     */
    @Test
    public void testLoadEmptyTasks() {
        Storage invalidStorage = new Storage("filepath_that_does_not_exist.txt");
        TaskList emptyTaskList = invalidStorage.loadTasks();
        assertEquals(0, emptyTaskList.getSize());
    }
}
