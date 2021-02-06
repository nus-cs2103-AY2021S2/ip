package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestStorage {

    @Test
    public void testLoadTasks() {
        Storage invalidStorage = new Storage("filepath_that_does_not_exist.txt");
        TaskList emptyTaskList = invalidStorage.loadTasks();
        assertEquals(0, emptyTaskList.getSize());
    }
}
