package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DukeFileWriter {

    public static void saveTaskListInInternalStorage(List<Task> taskList, String filePath) {
        try {
            String toBeSavedTaskListString = "";
            for (int i = 0; i < taskList.size(); i++) {
                String currentLine = "";
                Task task = taskList.get(i);
                if (task instanceof Event) {
                    currentLine += "E|";
                } else if (task instanceof Todo) {
                    currentLine += "T|";
                } else if (task instanceof Deadline) {
                    currentLine += "D|";
                }
                if (task.getIsTaskCompleted()) {
                    currentLine += "1|";
                } else {
                    currentLine += "0|";
                }
                currentLine += task.getTaskName();
                if (task instanceof Event) {
                    currentLine += "|" + ((Event) task).getDueAt();
                } else if (task instanceof Deadline) {
                    currentLine += "|" + ((Deadline) task).getDueBy();
                }
                if (i < taskList.size() - 1) {
                    toBeSavedTaskListString += currentLine + "\n";
                } else {
                    toBeSavedTaskListString += currentLine;
                }
            }
            writeToFile(toBeSavedTaskListString, filePath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void writeToFile(String textToAdd, String filePath) throws IOException {
        FileDirectoryChecker.prepareFile(filePath);
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
}
