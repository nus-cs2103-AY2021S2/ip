package duke;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    public void testNoneExistentPath() {
        Path p = Paths.get(".", "tmp");
        assert(!Files.exists(Paths.get(p.toString(), Storage.FILENAME)));
        Storage s = new Storage(p);
        assert(!Files.exists(Paths.get(p.toString(), Storage.FILENAME)));
        File f = new File(Paths.get(p.toString(), Storage.FILENAME).toString());
        f.delete();
    }
}
