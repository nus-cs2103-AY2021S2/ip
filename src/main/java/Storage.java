import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Storage {

    // Source folder
    private final static String sourceFolder = "data";
    // Name of text file to store tasks
    private final static String sourceFile = "/Duke.txt";

    /**
     * Creating directory if it does not exist.
     */
    public void createDirectory() {
        File directory = new File(sourceFolder);
        if (!directory.exists()) {
            boolean success = directory.mkdir();
            if (!success) {
                System.out.println("Directory creation was unsuccessful. Please " +
                        "manually create it.");
            }
        }
    }

    /**
     * Overwrites content to save new content to disk.
     */
    public void saveFile(String tasksToSave) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(sourceFolder + sourceFile,
                false));
        out.write(tasksToSave);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        Storage storage = new Storage();
        storage.loadFile();
    }

    /**
     * Method that loads primary file from local disk. Takes in the stored string in
     * text file stored locally, and parses it into a List containing tasks.
     * @return Task List
     * @throws IOException
     */
    public List<Task> loadFile() throws IOException {
        File directory = new File(sourceFolder + sourceFile);
        List<Task> taskList = new ArrayList<>();
        if (directory.exists()) {
            BufferedReader br =
                    new BufferedReader(new FileReader(sourceFolder + sourceFile));
            String line;
            while ((line = br.readLine()) != null) {

                String[] eachTask = line.split("[|]");
                String taskType = eachTask[0];
                assert eachTask.length >= 2;
                boolean isDone = eachTask[1].contains("X");
                assert eachTask.length >= 3;
                String description = eachTask[2];

                if (taskType.equals("T")) {
                    taskList.add(new Todo(description, isDone));
                } else if (taskType.equals("E")) {
                    assert eachTask.length >=4;
                    taskList.add(new Event(description, eachTask[3], isDone));
                } else {
                    assert eachTask.length >=4;
                    String eventDate = eachTask[eachTask.length - 1].replaceAll("\\s+",
                            "");
                    taskList.add(new Deadline(description, eventDate, isDone));
                }
            }
        }

        return taskList;
    }



}
