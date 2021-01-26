import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class DataStorage {
    private static final String DATA_FILE_PATH = "./data/tasks.json";
    public static List<Task> readTasks() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return Arrays.asList(mapper.readValue(new File(DATA_FILE_PATH), Task[].class));
    }
}
