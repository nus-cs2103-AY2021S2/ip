import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class StorageTest {

    @Test
    public void testLoadingData() throws IOException {
        Storage storage = new Storage();
        List<Task> taskList = storage.loadFile();
        System.out.println(taskList);

    }

}
