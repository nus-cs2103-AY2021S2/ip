import java.io.*;
import java.util.ArrayList;

public class Storage {

    private String fileDir;

    public Storage(String fileDir) {
        this.fileDir = fileDir;
    }

    /**
     * Loads TaskList saved on disk.
     * If TaskList does not exist, initialise an empty TaskList.
     *
     * @return TaskList saved on disk, if it does not exist, an empty TaskList is returned instead.
     * @throws DukeException If any error occurs while loading TaskList from disk.
     */
    public TaskList load() throws DukeException {
        try {
            FileInputStream fileIn = new FileInputStream(fileDir);
            ObjectInputStream input = new ObjectInputStream(fileIn);
            TaskList taskList = (TaskList) input.readObject();
            input.close();
            fileIn.close();
            return taskList;
        } catch (FileNotFoundException e) {
            // expected exception: file not found therefore no tasks loaded
            return new TaskList(new ArrayList<Task>());
        } catch (IOException | ClassNotFoundException e) {
            throw new DukeException("☹ Sorry, something went wrong while I was loading saved data from file.");
        }
    }

    /**
     * Saves TaskList saved to disk.
     *
     * @param taskList TaskList to be stored to disk.
     * @throws DukeException If any error occurs while saving TaskList to disk.
     */
    public void save(TaskList taskList) throws DukeException {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileDir);
            ObjectOutputStream output = new ObjectOutputStream(fileOut);
            output.writeObject(taskList);
            output.close();
            fileOut.close();
        } catch (IOException e) {
            throw new DukeException("☹ Sorry, something went wrong while I was saving data to file.");
        }
    }
}
