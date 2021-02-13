package duke;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import duke.task.Task;

/**
 * Storage class which reads/writes to files after any changes to the TaskList.
 */
public class Storage {
    private static final String FILE_NAME = "duke.txt";
    private final TaskList tasks;

    /**
     * Constructor method for Storage class.
     *
     * @param tasks TaskList object which contains the current tasks
     */
    Storage(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Attempts to read the file named "duke.txt".
     * @throws IOException
     * @throws IllegalArgumentException
     */
    public void read() throws IOException, IllegalArgumentException {
        FileReader reader = new FileReader(FILE_NAME);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String taskString;
        taskString = bufferedReader.readLine();
        while (taskString != null) {
            String[] taskArray = taskString.split("\\|");
            this.tasks.addTask(taskArray);
            taskString = bufferedReader.readLine();
        }
        reader.close();
    }

    /**
     * Attempts to write to the file named "duke.txt". Will throw
     * IOException if file could not be written.
     * @throws IOException
     */
    public void write() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (Task task: tasks.getTasks()) {
            sb.append(task.toFileString());
            sb.append('\n');
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        FileWriter writer = new FileWriter(FILE_NAME);
        writer.write(sb.toString());
        writer.close();
    }
}
