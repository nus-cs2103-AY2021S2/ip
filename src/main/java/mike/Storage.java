package mike;

import mike.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to store or load from the task lists into the disk
 */
public class Storage {
    private Path filePath;

    /**
     * Creates a Storage object corresponding to the path
     *
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Saves the taskList into the file on the disk corresponding to the filePath
     *
     * @param taskList the task list based on the user's input
     * @throws IOException
     */
    public void save(TaskList taskList) throws IOException {
        Path dirPath = filePath.getParent();
        if (!Files.exists(dirPath)) {
            Files.createDirectory(dirPath);
        }
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
        FileWriter fw = new FileWriter(filePath.toString());

        String textToAdd = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            textToAdd += currentTask + "\n";
        }
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Loads the task list from the filePath
     *
     * @return a list of tasks corresponding to the filePath
     * @throws IOException
     */
    public List<Task> loadTasks() throws IOException {
        List<Task> taskList = new ArrayList<>();
        File f = new File(filePath.toString());
        if (!f.exists()) {
            throw new ParseException("OOPS!!! The file does not exist");
        }
        List<String> stringList = Files.readAllLines(filePath);
        for (String line : stringList) {
            Task task = Parser.parseLineInFile(line);
            taskList.add(task);
        }

        return taskList;
    }
}
