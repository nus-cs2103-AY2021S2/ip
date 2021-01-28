import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    public void test1() {
        String expected = "C:" + File.separator + "Users" + File.separator + "xindi";
        assertEquals(expected, Storage.getHome());
    }

    public void test2() {
        String expected = "C:" + File.separator + "Users" + File.separator + "xindi" + File.separator + "task.txt";
        assertEquals(expected, Storage.getDefaultFilePath());
    }
}