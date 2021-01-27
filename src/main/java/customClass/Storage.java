package customClass;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Storage {
    private String path;

    public Storage(String path) {
        this.path = path;
    }

    public String loadData() {
        Load dataLoader = new Load();
        try {
            return dataLoader.loadData(path);
        } catch (IOException e) {
            // Do not output error message, instead, create the dir and file
            File dir = new File("src/data");
            dir.mkdir();
            File file = new File("src/data/tasks.txt");
            try {
                file.createNewFile();
            } catch (IOException err) {
                System.out.println("Error: " + err);
            }
            return "";
        }
    }

    public void save(List<Task> list) {
        try {
            FileWriter fw = new FileWriter(path);

            for (Task task : list) {
                fw.write(task.saveString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error :" + e);
        }
    }
}
