import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    public void load_emptyFile_emptyListReturned() throws FileNotFoundException {
        ArrayList<String> emptyList = new ArrayList<>();
        assertEquals(emptyList, new Storage("./emptyFile.txt").load());
    }

}
