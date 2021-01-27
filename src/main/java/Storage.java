import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public class Storage {
    private final String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    private Task processData(String type, String bool, String[] commands)
            throws DukeExceptionCorruptedData {
        if (type.equals("T")) {
            return bool.equals("1") ? new Todo(commands[0], true) : new Todo(commands[0]);
        } else if (type.equals("D")) {
            try {
                return bool.equals("1") ? new Deadline(commands[0], commands[1], true)
                        : new Deadline(commands[0], commands[1]);
            } catch (DukeExceptionDeadline e) {
                throw new DukeExceptionCorruptedData("The deadline task in the txt file corrupted");
            }
        } else if (type.equals("E")) {
            return bool.equals("1") ? new Event(commands[0], commands[1], true)
                    : new Event(commands[0], commands[1]);
        }
        throw new DukeExceptionCorruptedData("The txt file is corrupted");
    }

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
        } catch (FileNotFoundException e) {
            try {
                file.createNewFile();
            } catch(IOException error) {
                throw new DukeExceptionFolder("No 'data' folder in this directory");
            }
        }
        return tasks;
    }

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
