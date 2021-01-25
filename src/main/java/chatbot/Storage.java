package chatbot;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

import chatbot.tasks.Task;
import chatbot.exceptions.FileIoException;

/**
 * Represents the storage of the task data file.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns an arraylist of String command lines
     *
     * @return An ArrayList of String
     */
    public ArrayList<String> loadTaskList() throws FileIoException {
        try {
            ArrayList<String> storedTaskList = new ArrayList<>();
            File f = new File(this.filePath);
            if (!f.exists()) {
                f.getParentFile().mkdir();
                f.createNewFile();
            }
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                storedTaskList.add(s.nextLine());
            }
            return storedTaskList;
        } catch (IOException e) {
            throw new FileIoException();
        }
    }
    /**
     * Writes into the current TaskList into file.
     *
     * @param taskHandler which handles the tasks.
     * @throws FileIoException when there is a IOException
     */
    public void writeToFile(TaskHandler taskHandler) throws FileIoException {
        try {
            ArrayList<Task> taskList = taskHandler.getTaskList();
            FileWriter fw = new FileWriter(this.filePath);

            for (int i = 0; i < taskList.size(); i++) {
                fw.write(taskList.get(i).writeToFileFormat() + "\n");
            }
            fw.close();

        } catch (IOException e) {

            throw new FileIoException();

        }
    }
}

