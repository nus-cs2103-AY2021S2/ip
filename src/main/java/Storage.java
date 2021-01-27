import java.io.*;
import java.util.ArrayList;

public class Storage {

    private String fileDir;

    public Storage(String fileDir) {
        this.fileDir = fileDir;
    }

    public TaskList load() throws DukeException {
        try {
            FileInputStream fileIn = new FileInputStream(fileDir);
            ObjectInputStream input = new ObjectInputStream(fileIn);
//            ArrayList<Task> taskList = (ArrayList<Task>) input.readObject();
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
