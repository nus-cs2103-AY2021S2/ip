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
        System.out.println("1");
        ArrayList<Task> taskList = new ArrayList<>();
        boolean newFile = false;
        try {
            if (tasks.createNewFile()) {
                System.out.println("Created new file " + tasks.getName());
                System.out.println("Location: " + tasks.getAbsolutePath());
                newFile = true;
            } else {
                System.out.println("Used existing file");
                System.out.println("Location: " + tasks.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("An error occurred when trying to create the file");
        }
        if (!newFile) {
            return deserialize();
        } else {
            return taskList;
        }
    }

    public void write(List<Task> taskList) {
        File tasks = new File(filePath);
        System.out.println("10");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(tasks);
            System.out.println("ll");
            ObjectOutputStream objOutputStream = new ObjectOutputStream(fileOutputStream);
            objOutputStream.writeObject(taskList);
            System.out.println("here");
            objOutputStream.flush();
            objOutputStream.close();
            System.out.println("Wrote to file");
            System.out.println("Location: " + tasks.getAbsolutePath());
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("An error occurred");
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
