package owen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import owen.task.TaskList;

/**
 * Storage handles reading and writing task list to file on disk.
 */
public class Storage {
    private final File file;

    /**
     * Create storage backed by a file on disk.
     *
     * @param path Path to file on disk.
     */
    public Storage(String path) {
        this.file = new File(path);
    }

    /**
     * Reads TaskList from disk.
     *
     * @return TaskList read from disk.
     */
    public TaskList readTaskList() {
        try {
            TaskList taskList = new TaskList();

            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNextLine()) {
                String taskString = scanner.nextLine();
                try {
                    taskList = taskList.deserializeTask(taskString);
                } catch (OwenException exception) {
                    System.err.println(exception.getMessage());
                }
            }
            scanner.close();

            return taskList;
        } catch (FileNotFoundException exception) {
            return new TaskList();
        }
    }

    /**
     * Writes TaskList to disk.
     *
     * @param taskList TaskList to write.
     * @return this Storage.
     * @throws OwenException Error writing to disk.
     */
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
