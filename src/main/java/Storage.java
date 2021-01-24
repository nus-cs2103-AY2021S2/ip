import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    private final static String DATA_DIR = "./data";
    private File saveFile = new File(DATA_DIR + "save.txt");
    private static Storage INSTANCE = null;

    private Storage() {

    }

    public static Storage getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Storage();
        }
        return INSTANCE;
    }

    public void update(ArrayList<Task> tasks) {
        try {
            Path dataPath = Paths.get(DATA_DIR);
            Files.createDirectories(dataPath);
            File saveFile = new File("./data/save.txt");
            FileWriter fw = new FileWriter(saveFile);
            fw.write(obtainTasks(tasks));
            fw.close();
        } catch (IOException e) {
            System.out.println("Failed to create data directory" + e.getMessage());
        }
    }

    private String obtainTasks(ArrayList<Task> tasks) {
        String res = "";
        for (Task task : tasks) {
            res = res.concat(task.data() + "\n");
        }
        return res;
    }
}
