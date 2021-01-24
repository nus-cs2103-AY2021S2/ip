import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() {
        //Dukedata/tasklist.txt
        File tasks = new File(filePath);
        ArrayList<Task> taskList = new ArrayList<>();
        boolean newFile = false;
        try {
            if (tasks.createNewFile()) {
                System.out.println("Created new file " + tasks.getName());
                newFile = true;

            }
        } catch (IOException e) {
            System.out.println("An error occured when trying to create the file");
        }
        if (!newFile) {
            return deserialize();
        } else {
            return taskList;
        }
    }

    public List<Task> deserialize() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objInputStream = new ObjectInputStream(fileInputStream);
            taskList = (ArrayList<Task>) objInputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("An error occurred");
        } catch (ClassNotFoundException e) {
            System.out.println("Reinstall Duke");
        }
        return taskList;
    }

}
