package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.ui.Ui;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Writes file input into internal storage
 */
public class DukeFileWriter {

    /**
     * Saves the task list into internal storage
     * @param taskList the current instance of task list used by Duke
     * @param filePath the file path of the internal storage to save at
     */
    public static void saveTaskListInInternalStorage(List<Task> taskList, String filePath) {
        try {
            String toBeSavedTaskListString = "";
            for (int i = 0; i < taskList.size(); i++) {
                String currentLine = "";
                Task task = taskList.get(i);
                currentLine += task.getFileWriterIdentifier()
                        + getTaskCompletedStringIndicator(task)
                        + task.getTaskName()
                        + getTaskDate(task);
                toBeSavedTaskListString = (i < taskList.size() - 1)
                        ? toBeSavedTaskListString + currentLine + "\n"
                        : toBeSavedTaskListString + currentLine;
            }
            writeToFile(toBeSavedTaskListString, filePath);
        } catch (IOException e) {
            Ui.printError(e.getMessage());
        }
    }

    private static String getTaskDate(Task task) {
        if (task instanceof Event) {
            return "|" + ((Event) task).getDueAt();
        } else if (task instanceof Deadline) {
            return "|" + ((Deadline) task).getDueBy();
        } else {
            return "";
        }
    }

    private static String getTaskCompletedStringIndicator(Task task) {
        if (task.getIsTaskCompleted()) {
            return "1|";
        } else {
            return "0|";
        }
    }

    private static void writeToFile(String textToAdd, String filePath) throws IOException {
        FileDirectoryChecker.prepareFile(filePath);
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
}
