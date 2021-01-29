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
    private static final String filePath = "./src/main/java/duke/data/data.txt";

    /** Retrieve data file if present
     * Else create new data file
     * @return file
     * @throws DukeException
     */
    public static File getFile() throws DukeException {

        File file = new File(filePath);
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


    /** Save task into data file
     * @param taskAL array list of task
     * @throws DukeException
     */
    public static void save(ArrayList<Task> taskAL) throws DukeException {
        try {
            File file = getFile();
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < taskAL.size(); i++) {
                writer.write(taskAL.get(i).changeFormat() + '\n');
                writer.flush();
            }
            writer.close();
        } catch (Exception e) {
            throw new DukeException("error writing into file");
        }

    }


    /** Load data in file when program starts
     * @return array lise of task
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
