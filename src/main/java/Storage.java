import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final File file;

    public Storage(String path) {
        this.file = new File(path);
    }

    public TaskList readTaskList() {
        try {
            TaskList taskList = new TaskList();

            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNextLine()) {
                String taskString = scanner.nextLine();
                taskList = taskList.deserializeTask(taskString);
            }
            scanner.close();

            return taskList;
        } catch (FileNotFoundException exception) {
            return new TaskList();
        }
    }

    public Storage writeTaskList(TaskList taskList) throws OwenException {
        try {
            this.file.getParentFile().mkdirs();
            this.file.createNewFile();

            FileWriter fileWriter = new FileWriter(this.file);
            String taskListString = taskList.serialize();
            fileWriter.write(taskListString);
            fileWriter.close();
        } catch (IOException exception) {
            throw new OwenException("Could not write task list to storage...");
        }

        return this;
    }
}
