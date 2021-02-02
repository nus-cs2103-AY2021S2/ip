package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class StorageTest {

    @Test
    void save_file_success() {
        try {
            Storage testStorage = new Storage("StorageTest.txt");
            Task temp1 = new ToDo("event do tasks /at 2020-01-15");
            List<Task> saveList = new ArrayList<>();
            saveList.add(temp1);
            testStorage.save(new TaskList(saveList));
            File f = new File("StorageTest.txt");
            assertEquals(true, f.exists());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void load_file_success() {
        try {
            Storage testStorage = new Storage("StorageTest.txt");
            Task expectedTask = new ToDo("event do tasks /at 2020-01-15");
            List<Task> storeageTasks = testStorage.load();
            assertEquals(false, storeageTasks.isEmpty());
            assertEquals(expectedTask, storeageTasks.get(0));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
