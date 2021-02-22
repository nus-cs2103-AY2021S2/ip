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
    private final String fileName;

    /**
     * Constructor for Storage object
     * @param filePath String representing relative path of the txt file
     */
    Storage(String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
    }

    /**
     * Load the data from a line of text to be a Task object
     * @param type String representing type of Task
     * @param bool String representing the state of the Task
     * @param commands Description of the task
     * @return Task corresponding to the type, bool, abd commands
     * @throws DukeExceptionCorruptedData The data format is incompatible
     */
    private Task processData(String type, String bool, String[] commands)
            throws DukeExceptionCorruptedData {
        switch (type){
        case "T":
            return bool.equals("1") ? new Todo(commands[0], true) : new Todo(commands[0]);
        case "D":
            try {
                return bool.equals("1") ? new Deadline(commands[0], commands[1], true)
                        : new Deadline(commands[0], commands[1]);
            } catch (DukeExceptionDeadline e) {
                throw new DukeExceptionCorruptedData("The deadline task in the txt file corrupted");
            }
        case "E":
            return bool.equals("1") ? new Event(commands[0], commands[1], true)
                    : new Event(commands[0], commands[1]);
        }
        throw new DukeExceptionCorruptedData("The txt file is corrupted");
    }

    /**
     * Load data from the txt file, If no txt file, create new file. If
     * no folder, create new folder.
     * @return List of Task object
     * @throws DukeExceptionCorruptedData The data format is incompatible
     */
    public TaskList load() throws DukeExceptionCorruptedData {
        List<Task> tasks = new ArrayList<>();
        File file = new File(this.filePath + this.fileName);
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] commands = line.split("\\|");
                tasks.add(processData(commands[0], commands[1], Arrays.copyOfRange(commands, 2, 5)));
            }
            return new TaskList(tasks);
        } catch (FileNotFoundException e) {
            try {
                file.createNewFile();
            } catch(IOException error) {
                new File(this.filePath).mkdir();
            }
        }
        return new TaskList();
    }

    /**
     * Write file to the existing file (if doesn't exist, create a new one)
     * @param tasks TaskList wanted to be written
     * @throws DukeException Wrong path
     */
    public void writeFile(TaskList tasks) throws DukeException {
        try {
            FileWriter file = new FileWriter(this.filePath + this.fileName, false);
            for (Task task: tasks.getTasks()) {
                file.write(String.format(task.saveString() + "%n"));
            }
            file.close();
        } catch(IOException e) {
            throw new DukeException("Wrong path");
        }
    }

}
