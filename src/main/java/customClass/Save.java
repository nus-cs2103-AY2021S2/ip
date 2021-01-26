package customClass;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Save {
    public void save(List<Task> list) {
        try {
            FileWriter fw = new FileWriter("src/data/tasks.txt");

            for (Task task : list) {
                fw.write(task.saveString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error :" + e);
        }
    }
}
