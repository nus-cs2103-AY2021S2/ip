import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void loadFileContents_test() throws IOException {
        String pathString = System.getProperty("user.dir") + "/data/dukeTest.txt";

        Storage storage = new Storage(pathString);
        ArrayList<Task> taskListActual = new ArrayList<>();
        ArrayList<Task> taskListExpected = new ArrayList<>();

        storage.loadFileContents(taskListActual);
        taskListExpected.add(new ToDos("read book", false));

        assertEquals(taskListExpected.get(0).toString(), taskListActual.get(0).toString());
    }
}
