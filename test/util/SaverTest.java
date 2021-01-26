package util;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SaverTest {

    @org.junit.jupiter.api.Test
    void writeSave() throws IOException {
        Saver.writeSave("sweg");
    }

    @Test
    void getFile() throws IOException {
        System.out.println(Saver.getFile().toString());
    }
}