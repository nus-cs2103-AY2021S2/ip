package duke.logging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.model.Deadline;
import duke.model.Event;
import duke.model.Task;
import duke.model.ToDo;

/**
 * A Storage class denotes a file storage.
 */
public class Storage {
    private final File file;

    /**
     * Constructs a storage
     * @param filePath   he filepath where the data are going to be stored at.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Load the tasks in the file.
     * @return                         A list of tasks in the file.
     * @throws FileNotFoundException   If file isn't found.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        ArrayList<Task> tasks = new ArrayList<>();
        while (sc.hasNext()) {
            Task task;
            String data = sc.nextLine();
            String[] strings = data.split("// ");
            char taskType = strings[0].charAt(0);
            boolean isCompleted = Integer.parseInt(strings[1].substring(0, 1)) == 1;
            String taskName = strings[2].strip();
            if (taskType == 'T') {
                task = new ToDo(isCompleted, taskName);
            } else if (taskType == 'D') {
                task = new Deadline(isCompleted, taskName, LocalDate.parse(strings[3].strip()));
            } else {
                task = new Event(isCompleted, taskName, LocalDate.parse(strings[3].strip()));
            }
            tasks.add(task);
        }
        return tasks;
    }

    /**
     * Overwrite the entire task's file content.
     * @param tasks         A list of tasks.
     * @throws IOException  If file cannot be written or open.
     */
    public void overwrite(ArrayList<Task> tasks) throws IOException {
        assert tasks != null : "Tasks cannot be null";
        FileWriter fw = new FileWriter(file);
        for (Task task : tasks) {
            fw.write(task.generateFileFormatString() + "\n");
        }
        fw.close();
    }

    /**
     * Append to the back of the task's file.
     * @param task          A list of tasks
     * @throws IOException  If file cannot be written or open.
     */
    public void append(Task task) throws IOException {
        assert task != null : "Tasks cannot be null";
        FileWriter fw = new FileWriter(file, true);
        fw.write(task.generateFileFormatString() + "\n");
        fw.close();
    }
}
