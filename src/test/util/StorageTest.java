package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

class StorageTest {

    @org.junit.jupiter.api.Test
    void writeSave() throws IOException {
        Storage.writeSave("sweg");
        assertEquals("sweg", Storage.readSave());
    }

    @Test
    void getFile() throws IOException {
        assertEquals("data\\sweh.txt", Storage.getFile().toString());
    }
}