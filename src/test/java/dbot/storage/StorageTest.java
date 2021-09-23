package dbot.storage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class StorageTest {
    @Test
    public void initStorage_illegalPath_invalidStorageFilePathException() {
        assertThrows(
                Storage.InvalidStorageFilePathException.class,
                        () -> { new Storage("invalid.invalid");});
        assertThrows(
                Storage.InvalidStorageFilePathException.class,
                        () -> { new Storage("txt");});
    }
}
