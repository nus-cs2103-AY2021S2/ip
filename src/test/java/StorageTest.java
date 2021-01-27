import duke.Storage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {
    String path = "./data/JUnitTesting.txt";
    File file = new File(path);

    @BeforeEach
    void setup() {
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void create_new_file() {
        Storage.createStorage(path);
        assertTrue(new File(path).exists());
    }

    @Test
    void store_task_in_file() throws FileNotFoundException {
        Storage storage = Storage.createStorage(path);
        storage.storeTask("stored task successfully");
        Scanner sc = new Scanner(new File(path));
        String content = "";
        if (sc.hasNextLine()) {
            content = sc.nextLine();
        }
        assertEquals("0 stored task successfully", content);
    }

    @AfterEach
    void teardown() {
        if (file.exists()) {
            file.delete();
        }
    }
}
