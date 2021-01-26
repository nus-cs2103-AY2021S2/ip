package duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void test_load_empty() throws DukeException {
        ArrayList<String> testList = new ArrayList<>();
        assertEquals(testList, new Storage("./testLoad.txt").load());
    }
}
