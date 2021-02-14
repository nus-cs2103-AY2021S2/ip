import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class StorageTest {

    @Test
    public void load_noSuchFile() throws DukeException {
        Storage storage = new Storage("data/tasks.txt");
        ArrayList<Task> task = storage.load();
        assertEquals(task, new ArrayList<>());
    }
}
