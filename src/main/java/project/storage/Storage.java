package project.storage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import project.Olaf;
import project.command.AddDeadline;
import project.command.AddEvent;
import project.command.AddTodo;
import project.task.Task;
import project.task.TaskList;

/**
 * Handles storing and loading of application data from local file.
 * All {@code Task}s are stored and read from this file.
 */
public class Storage {
    private Path dataPath;
    private boolean isSaveSuccessful = false;

    /**
     * Creates an instance of {@code Storage}.
     *
     * @param filePath The path to the file where all the tasks are to be stored.
     */
    public Storage(String filePath) {
        assert filePath.equals(Olaf.FILE_PATH);

        String[] filePathSplit = filePath.split("(?:.(?!/))+$", 2);
        String fileDirectory = filePathSplit[0];

        // Solution adapted from: https://stackoverflow.com/questions/28947250/..
        // ..create-a-directory-if-it-does-not-exist-and-then-create-the-files-in-that-direct
        // inserts correct file path separator on *nix and Windows
        File directory = new File(fileDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }

        assert directory.exists();
        this.dataPath = Paths.get(filePath);
    }

    /**
     * Saves all the tasks to the local file.
     * Overwrites the file everytime this method executes.
     *
     * @param taskList The {@code TaskList} of containing all the tasks to be saved.
     */
    public void saveData(TaskList taskList) {
        try {
            // save all tasks again if taskList has tasks
            if (taskList.hasTasks()) {
                String taskListToString = taskList.toString();
                // splitting by "  %d. [" in case the task description uses periods and digits as well
                ArrayList<String> tasksWithoutIndex = new ArrayList<>(
                        List.of(taskListToString.split("  \\d. \\[")));
                String toSave = tasksWithoutIndex.stream()
                        .reduce((a, b) -> a + b)
                        .get();

                // Solution adapted from: https://attacomsian.com/blog/java-save-string-to-text-file
                // write string to a file
                Files.writeString(dataPath,
                        toSave,
                        StandardCharsets.UTF_8,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING);
            } else {
                Files.deleteIfExists(dataPath);
            }
            this.isSaveSuccessful = true;
        } catch (IOException e) {
            this.isSaveSuccessful = false;
            e.printStackTrace();
        }
    }

    /**
     * Returns if last {@code saveData()} call was successful.
     */
    public boolean isSaved() {
        return this.isSaveSuccessful;
    }

    /**
     * Reads all the tasks from the local file.
     *
     * @throws IOException If there is nothing stored.
     */
    public ArrayList<Task> load() throws IOException {
        return Files.readAllLines(dataPath,
                StandardCharsets.UTF_8)
                .stream()
                .map(this::readTaskFromData)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private Task readTaskFromData(String line) {
        boolean taskIsDone = (line.charAt(3) == 'X');
        Task outputTask = null;

        if (line.startsWith("T]")) {
            outputTask = new AddTodo().loadFromStorage(line);
        } else if (line.startsWith("D]")) {
            outputTask = new AddDeadline().loadFromStorage(line);
        } else if (line.startsWith("E]")) {
            outputTask = new AddEvent().loadFromStorage(line);
        }
        assert outputTask != null;
        if (taskIsDone) {
            outputTask.markAsDone();
        }
        return outputTask;
    }
}
