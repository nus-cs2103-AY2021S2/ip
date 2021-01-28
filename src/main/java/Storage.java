import java.io.File;
import java.io.IOException;

public class Storage {
    private File file;

    /**
     * Constructor to initialize storage
     */
    public Storage(String filePath) throws IOException {
        file = new File(filePath);
        file.getParentFile().mkdirs();
    }
}
