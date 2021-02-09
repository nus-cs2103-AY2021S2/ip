package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import duke.duke.Duke;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Used to write to a file.
 */
public class FileWriting {

    /**
     * Saves list of tasks by writing into the file whenever a command updates the task list.
     * @param file The file that is written to.
     * @param duke A Duke object that manages task list operations.
     * @throws IOException If error occurs while writing to the file.
     */
    public static void writeToFile(File file, Duke duke) throws IOException {
        FileWriter fw = new FileWriter(file);
        List<Task> taskList = duke.getList().getLst();
        StringBuilder tasks = writeTasks(taskList);
        fw.write(tasks.toString());
        fw.close();
    }

    public static StringBuilder writeTasks(List<Task> taskList) {
        StringBuilder tasks = new StringBuilder();
        for (Task curr : taskList) {
            if (curr.getStatus()) {
                textForCompletedTasks(curr, tasks);
            } else {
                textForUncompletedTasks(curr, tasks);
            }
        }
        return tasks;
    }

    public static void textForCompletedTasks(Task curr, StringBuilder textToAdd) {
        if (curr instanceof Todo) {
            textToAdd.append(String.format("T | 1 | %s%n", curr.getDescription()));
        } else if (curr instanceof Deadline) {
            textToAdd.append(String.format("D | 1 | %s | %s%n", curr.getDescription(), (
                    (Deadline) curr).getDeadline()));
        } else if (curr instanceof Event) {
            textToAdd.append(String.format("E | 1 | %s | %s%n", curr.getDescription(), (
                    (Event) curr).getEventTime()));
        }
    }

    public static void textForUncompletedTasks(Task curr, StringBuilder textToAdd) {
        if (curr instanceof Todo) {
            textToAdd.append(String.format("T | 0 | %s%n", curr.getDescription()));
        } else if (curr instanceof Deadline) {
            textToAdd.append(String.format("D | 0 | %s | %s%n", curr.getDescription(), (
                    (Deadline) curr).getDeadline()));
        } else if (curr instanceof Event) {
            textToAdd.append(String.format("E | 0 | %s | %s%n", curr.getDescription(), (
                    (Event) curr).getEventTime()));
        }
    }
}
