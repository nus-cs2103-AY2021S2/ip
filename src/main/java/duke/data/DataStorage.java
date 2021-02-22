package duke.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Task;

public class DataStorage {
    private static final String FILE_PATH = "data/listOfTask.txt";

    /**
     *  Retrieve the data file if it is present. If not, a new data file will be created
     * @return file
     * @throws DukeException
     */
    public static File getFile() throws DukeException {

        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs();

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                throw new DukeException("Error creating file");
            }
        }
        return file;
    }

    /**
     * Save all tasks in the task list into data file
     * @param taskArrayList task list
     * @throws DukeException
     */
    public static void save(ArrayList<Task> taskArrayList) throws DukeException {
        try {
            File file = getFile();
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < taskArrayList.size(); i++) {
                writer.write(taskArrayList.get(i).changeFormat() + '\n');
                writer.flush();
            }
            writer.close();
        } catch (Exception e) {
            throw new DukeException("error writing into file");
        }
    }

    /**
     * Load the file data to the application when program starts
     * @return task list to be displayed
     * @throws DukeException
     * @throws IOException
     */
    public static ArrayList<Task> loadFile() throws DukeException, IOException {
        File file = getFile();
        Task t = new Task();
        ArrayList<Task> taskAL = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                taskAL.add(t.changeToTaskFormat(s));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Error finding file");
        }
        return taskAL;
    }

}
