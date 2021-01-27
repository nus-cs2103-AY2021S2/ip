import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

/**
 * A class that control the data saving and loading in Duke Application.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor for Storage object
     * @param filePath String representing relative path of the txt file
     */
    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load the data from a line of text to be a Task object
     * @param type String representing type of Task
     * @param bool String representing the state of the Task
     * @param commands Description of the task
     * @return Task corresponding to the type, bool, abd commands
     * @throws DukeExceptionCorruptedData The data format is incompatible
     */
    private Task processData(String type, String bool, String[] commands) throws DukeExceptionCorruptedData {
        if (type.equals("T")) {
            return bool.equals("1") ? new Todo(commands[0], true) : new Todo(commands[0]);
        }
        else if (type.equals("D")) {
            try {
                return bool.equals("1") ? new Deadline(commands[0], commands[1], true) : new Deadline(commands[0], commands[1]);
            } catch (DukeExceptionDeadline e) {
                throw new DukeExceptionCorruptedData("The deadline task in the txt file corrupted");
            }
        }
        else if (type.equals("E")) {
            return bool.equals("1") ? new Event(commands[0], commands[1], true) : new Event(commands[0], commands[1]);
        }
        throw new DukeExceptionCorruptedData("The txt file is corrupted");
    }

    /**
     * Load data from the txt file
     * @return List of Task object
     * @throws DukeExceptionFolder The folder doesn't exist
     * @throws DukeExceptionCorruptedData The data format is incompatible
     */
    public List<Task> load() throws DukeExceptionFolder,DukeExceptionCorruptedData {
        List<Task> tasks = new ArrayList<>();
        File file = new File(this.filePath);
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] commands = line.split("\\|");
                tasks.add(processData(commands[0], commands[1], Arrays.copyOfRange(commands, 2, 5)));
            }
            return tasks;
        }
        catch (FileNotFoundException e) {
            try {
                file.createNewFile();
            } catch(IOException error) {
                throw new DukeExceptionFolder("No 'data' folder in this directory");
            }
        }
        return tasks;
    }

    /**
     * Write file to the existing file (if doesn't exist, create a new one)
     * @param tasks TaskList wanted to be written
     * @throws DukeException Wrong path
     */
    public void writeFile(TaskList tasks) throws DukeException {
        try {
            FileWriter file = new FileWriter(this.filePath, false);
            for (Task task: tasks.getTasks()) {
                file.write(String.format(task.saveString() + "%n"));
            }
            file.close();
        } catch(IOException e) {
            throw new DukeException("Wrong path");
        }
    }

}
