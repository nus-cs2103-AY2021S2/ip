package duke.storage;

import duke.duke.Duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;

/**
 * Used to write to a file.
 */
public class FileWriting {

    /**
     * Saves list of tasks by writing into the file whenever a command updates the task list.
     * @param file the file that is written to
     * @param duke a Duke object that manages task list operations
     * @throws IOException if error occurs while writing to the file
     */
    public static void writeToFile(File file, Duke duke) throws IOException {
        FileWriter fw = new FileWriter(file);
        List<Task> taskList = duke.getList().getLst();
        StringBuilder textToAdd = new StringBuilder();

        for (Task curr : taskList) {
            if (curr.getStatus()) {
                if (curr instanceof Todo) {
                    textToAdd.append(String.format("T | 1 | %s%n", curr.getDescription()));
                } else if (curr instanceof Deadline) {
                    textToAdd.append(String.format("D | 1 | %s | %s%n", curr.getDescription(),
                            ((Deadline) curr).getDeadline()));
                } else if (curr instanceof Event) {
                    textToAdd.append(String.format("E | 1 | %s | %s%n", curr.getDescription(),
                            ((Event) curr).getEventTime()));
                }
            } else {
                if (curr instanceof Todo) {
                    textToAdd.append(String.format("T | 0 | %s%n", curr.getDescription()));
                } else if (curr instanceof Deadline) {
                    textToAdd.append(String.format("D | 0 | %s | %s%n", curr.getDescription(),
                            ((Deadline) curr).getDeadline()));
                } else if (curr instanceof Event) {
                    textToAdd.append(String.format("E | 0 | %s | %s%n", curr.getDescription(),
                            ((Event) curr).getEventTime()));
                }
            }
        }

        fw.write(textToAdd.toString());
        fw.close();
    }
}
