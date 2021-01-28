import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DukeFileWriter {
    private static final String FILE_PATH = "data" + File.separator + "duke.txt";

    public static void saveTaskListInInternalStorage(List<Task> taskList) {
        try {
            String toBeSavedTaskListString = "";
            for (int i = 0; i < taskList.size(); i++) {
                String currentLine = "";
                Task task = taskList.get(i);
                if (task instanceof Events) {
                    currentLine += "E|";
                } else if (task instanceof Todos) {
                    currentLine += "T|";
                } else if (task instanceof Deadlines) {
                    currentLine += "D|";
                }
                if (task.getIsTaskCompleted()) {
                    currentLine += "1|";
                } else {
                    currentLine += "0|";
                }
                currentLine += task.getTaskName();
                if (task instanceof Events) {
                    currentLine += "|" + ((Events) task).getDueAt();
                } else if (task instanceof Deadlines) {
                    currentLine += "|" + ((Deadlines) task).getDueBy();
                }
                if (i < taskList.size() - 1) {
                    toBeSavedTaskListString += currentLine + "\n";
                } else {
                    toBeSavedTaskListString += currentLine;
                }
            }
            writeToFile(toBeSavedTaskListString);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void writeToFile(String textToAdd) throws IOException {
        FileDirectoryChecker.doesFileExist(FILE_PATH);
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write(textToAdd);
        fw.close();
    }

    public static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true);
        fw.write(textToAppend);
        fw.close();
    }
}
