import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;

public class Storage {

    public TaskList readFromFile() {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdir();
        }
        File saved_tasks = new File(dir, "saved_tasks");
        if (!saved_tasks.exists()) {
            try {
                saved_tasks.createNewFile();
                return new TaskList(new ArrayList<>());
            } catch (IOException e) {
                e.printStackTrace();
                return new TaskList(new ArrayList<>());
            }
        } else {
            try {
                FileInputStream fis = new FileInputStream(saved_tasks);
                ObjectInputStream ois = new ObjectInputStream(fis);
                TaskList result = (TaskList) ois.readObject();
                ois.close();
                fis.close();
                return result;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return new TaskList(new ArrayList<>());
            }
        }
    }

    public void writeToFile(TaskList tasks) {
        File dir = new File("data");
        File saved_tasks = new File(dir, "saved_tasks");
        try {
            FileOutputStream fos = new FileOutputStream(saved_tasks);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
