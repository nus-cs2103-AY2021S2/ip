package util;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class StorageTest {

    @org.junit.jupiter.api.Test
    void writeSave() throws IOException {
        Storage.writeSave("sweg");
    }

    @Test
    void getFile() throws IOException {
        System.out.println(Storage.getFile().toString());
    }
}