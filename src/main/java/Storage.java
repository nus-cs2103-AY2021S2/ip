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


    private final static String sourceFolder = "data";
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
                    taskList.add(new Deadline(description, eachTask[3], isDone));
                }
            }
        }

        return taskList;
    }



}
