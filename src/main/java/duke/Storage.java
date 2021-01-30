package duke;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

/**
 * Used to store to or load from the task lists into the disk
 */
public class Storage {

    private Path filePath;

    /**
     * Creates a storage corresponding to the path.
     *
     * @param path the location of file in the disk.
     */
    public Storage(String path) {
        this.filePath = Paths.get(path);
    }

    /**
     * Loads the task list file from filePath.
     *
     * @return a list of task corresponding to the file.
     * @throws IOException if such filePath does not exist.
     */
    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        List<String> strings = Files.readAllLines(filePath);
        for (String line : strings) {
            Task task = Parser.parseInFile(line);
            tasks.add(task);
        }
        return tasks;
    }

    /**
     * Saves the task list into the file in the disk corresponding to the filePath.
     *
     * @param taskList the task list that users input.
     */
    public void save(TaskList taskList) {
        try {
            Path directoryPath = filePath.getParent();
            if (!Files.exists(directoryPath)) {
                Files.createDirectory(directoryPath);
            }
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            BufferedWriter bufferedWriter = Files.newBufferedWriter(filePath);
            for (Task task : taskList.getTasks()) {
                bufferedWriter.write(task.toString() + "\n");
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
