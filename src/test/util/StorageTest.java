package util;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

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