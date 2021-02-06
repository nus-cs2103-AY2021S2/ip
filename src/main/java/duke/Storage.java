package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exceptions.InvalidFolderException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * <code>Storage</code> class handles the loading of
 * tasks from the file and saving tasks in the file.
 */
public class Storage {

    /**
     * Constructor for Storage class.
     * Initializes File for the task file.
     *
     * @param filePath Filepath of the file that saves task
     * @throws InvalidFolderException If folder to store the file does not exist.
     */
    public Storage(String filePath) throws InvalidFolderException {
        try {
            File file = new File(filePath);

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } else {
                readFileContents(filePath);
            }
        } catch (IOException ex) {
            throw new InvalidFolderException();
        }
    }

    /**
     * Returns a List of existing tasks from the file when Duke starts up.
     *
     * @param filePath Filepath of the file that saves task.
     * @return List of existing tasks in the file.
     * @throws FileNotFoundException If the file cannot be found.
     */
    public List<Task> readFileContents(String filePath)
            throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        List<Task> list = new ArrayList<>();

        Task task;
        String taskDescription;
        LocalDateTime taskDate;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

        while (s.hasNextLine()) {
            String taskString = s.nextLine();
            String taskDone = taskString.substring(4, 5);
            int indexOfDivider = taskString.indexOf('|', 8);

            if (taskString.startsWith("T")) {
                taskDescription = taskString.substring(8);
                task = new ToDo(taskDescription);
            } else {
                taskDescription = taskString.substring(8, indexOfDivider);
                taskDate = LocalDateTime.parse(taskString.substring(indexOfDivider + 2), df);

                if (taskString.startsWith("D")) {
                    task = new Deadline(taskDescription, taskDate);
                } else {
                    assert taskString.startsWith("E"): "Invalid task string";
                    task = new Event(taskDescription, taskDate);
                }
            }

            if (taskDone.equals("1")) {
                task.markAsDone();
            }
            list.add(task);
        }
        return list;
    }

    /**
     * Saves the tasks in the file whenever the task list changes.
     *
     * @param filePath Filepath of the file that saves task.
     * @param list List of existing tasks.
     */
    public void overWriteFile(String filePath, List<Task> list) {
        try {
            FileWriter fw = new FileWriter(filePath);

            for (Task task : list) {
                boolean done = task.isDone();
                String doneString = "0";
                String taskDescription = task.getDescription();
                String taskDate;

                if (done) {
                    doneString = "1";
                }

                if (task.getTaskType().equals("ToDo")) {
                    fw.write("T | " + doneString
                            + " | " + taskDescription + "\n");
                } else {
                    taskDate = task.getTaskDate();

                    if (task.getTaskType().equals("Deadline")) {
                        fw.write("D | " + doneString
                                + " | " + taskDescription + "| " + taskDate + "\n");
                    } else {
                        fw.write("E | " + doneString
                                + " | " + taskDescription + "| " + taskDate + "\n");
                    }
                }
            }
            fw.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
