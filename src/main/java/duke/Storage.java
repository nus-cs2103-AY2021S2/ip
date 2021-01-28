package duke;

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
        File savedTasks = new File(dir, "saved_tasks");
        if (!savedTasks.exists()) {
            try {
                savedTasks.createNewFile();
                return new TaskList(new ArrayList<>());
            } catch (IOException e) {
                e.printStackTrace();
                return new TaskList(new ArrayList<>());
            }
        } else {
            try {
                FileInputStream fis = new FileInputStream(savedTasks);
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
        File savedTasks = new File(dir, "saved_tasks");
        try {
            FileOutputStream fos = new FileOutputStream(savedTasks);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
