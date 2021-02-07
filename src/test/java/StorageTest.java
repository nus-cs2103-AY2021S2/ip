import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.Storage;

public class StorageTest {
    private final String path = "./data/JUnitTesting.txt";
    private final File file = new File(path);

    @BeforeEach
    void setup() {
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void createNewFile() {
        Storage.createStorage(path);
        assertTrue(new File(path).exists());
    }

    @Test
    void storeTaskInFile() throws FileNotFoundException {
        Storage storage = Storage.createStorage(path);
        assert storage != null;
        String dummyTaskCommand = "stored task successfully";
        storage.storeTaskCommand(dummyTaskCommand);
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
