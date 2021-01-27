import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public static final String fileName = "tasks.data";

    /**
     * Save the task list to disk
     *
     * @param tasks task list
     */
    public static void saveToFile(ArrayList<Task> tasks) {
        try {
            File fileObj = new File(fileName);
            fileObj.createNewFile();
            FileWriter fileWriter = new FileWriter(fileName);
            // Convert task list to a string
            StringBuilder data = new StringBuilder();
            for (Task task : tasks) {
                data.append(task.toSavedString());
            }
            fileWriter.write(data.toString());
        } catch (IOException e) {
            Ui.printError(e);
        }
    }

    /**
     *
     */
    public static ArrayList<Task> readFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File fileObj = new File(fileName);
            fileObj.createNewFile();
            Scanner fileReader = new Scanner(fileObj);
            StringBuilder data = new StringBuilder();
            while (fileReader.hasNextLine()) {
                data.append(fileReader.nextLine());
            }
            fileReader.close();
            String[] lines = data.toString().split("\n");
            for (String line : lines) {
                String[] sections = line.split(" | ");
                if (sections[0].equals("T")) {
                    Task task = new TodoTask(sections[2]);
                    if (sections[1].equals("1")) {
                        task.setIsDone(true);
                    }
                    tasks.add(task);
                } else if (sections[0].equals("D")) {
                    Task task = new DeadlineTask(sections[2], sections[3]);
                    if (sections[1].equals("1")) {
                        task.setIsDone(true);
                    }
                    tasks.add(task);
                } else if (sections[0].equals("E")) {
                    Task task = new EventTask(sections[2], sections[3]);
                    if (sections[1].equals("1")) {
                        task.setIsDone(true);
                    }
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            Ui.printError(e);
        }
        return tasks;
    }
}
