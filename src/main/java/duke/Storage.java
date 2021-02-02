package duke;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Storage {
    private final String fileName;

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Save the contents to disk
     *
     * @param tasks the object to store the tasks
     */
    public void saveTasks(TaskList tasks) {
        try {
            FileOutputStream taskFile = new FileOutputStream(fileName);
            ObjectOutputStream taskObjectStream = new ObjectOutputStream(taskFile);
            Integer taskSize = tasks.size();
            taskObjectStream.writeObject(taskSize);
            for (AbstractTask task : tasks) {
                taskObjectStream.writeObject(task);
            }
            taskObjectStream.flush();
            taskObjectStream.close();
        } catch (IOException e) {
            //Create a new file anyways
        }
    }

    /**
     * Read tasks from disk
     *
     * @param tasks the object to store the tasks
     */
    public void readTasks(TaskList tasks) {
        try {
            FileInputStream taskFile = new FileInputStream(fileName);
            ObjectInputStream taskObjectStream = new ObjectInputStream(taskFile);
            Integer taskSize = (Integer) taskObjectStream.readObject();
            for (int i = 0; i < taskSize; i++) {
                AbstractTask task = (AbstractTask) taskObjectStream.readObject();
                tasks.add(task);
            }
            taskObjectStream.close();
        } catch (IOException e) {
            System.out.println("No saved task file, continuing with empty list!\n");
        } catch (ClassNotFoundException e) {
            System.out.println("Something went wrong, resetting task file!\n");
            File taskFile = new File("data/duke.txt");
            taskFile.delete();
        }
    }
}
