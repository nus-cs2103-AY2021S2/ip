package duke.storage;

import duke.exceptions.InvalidSaveFileFormatException;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {
    @Test
    public void testStorage_invalidFile_throwsFileNotFoundException() {
       Storage storage = new Storage("invalidFile.abc");
       assertThrows(FileNotFoundException.class, () -> storage.load());
    }

    @Test
    public void testStorage_invalidSaveTextFormat_throwsInvalidSaveFileFormatException() {
        Storage storage = new Storage(System.getProperty("user.dir") + "/java/duke/storage/StorageTest.txt");
        assertThrows(InvalidSaveFileFormatException.class, () -> storage.load());
    }

}
