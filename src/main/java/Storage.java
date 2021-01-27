import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;

public class Storage {

    public static ArrayList<Task> readFromFile() {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdir();
        }
        File saved_tasks = new File(dir, "saved_tasks");
        if (!saved_tasks.exists()) {
            try {
                saved_tasks.createNewFile();
                return new ArrayList<>();
            } catch (IOException e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        } else {
            try {
                FileInputStream fis = new FileInputStream(saved_tasks);
                ObjectInputStream ois = new ObjectInputStream(fis);
                ArrayList<Task> result = (ArrayList<Task>) ois.readObject();
                ois.close();
                fis.close();
                return result;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        }
    }

    public static void writeToFile(ArrayList<Task> list) {
        File dir = new File("data");
        File saved_tasks = new File(dir, "saved_tasks");
        try {
            FileOutputStream fos = new FileOutputStream(saved_tasks);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
