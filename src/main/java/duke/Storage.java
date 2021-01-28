package duke;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    public static final String FILENAME = "data.log";
    private final Path directoryPath;
    private File file = null;

    public Storage(Path directoryPath) {
        this.directoryPath = directoryPath;
    }

    public Storage() {
        this(Paths.get(".", "data"));
    }

    private File getFile() throws IOException {
        // Create file if does not exist
        if (file == null) {
            File directory = directoryPath.toFile();
            directory.mkdirs();
            File dataFile = new File(directory, FILENAME);
            dataFile.createNewFile();
            this.file = dataFile;
        }
        return this.file;
    }

    public TaskList readTasks() throws IOException {
        TaskList taskList = new TaskList();
        BufferedReader reader = new BufferedReader(new FileReader(getFile()));
        String line;

        while ((line = reader.readLine()) != null) {
            taskList.addTask(TaskList.parseTask(line));
        }

        return taskList;
    }

    public void writeTasks(TaskList tasks) throws IOException {
        FileWriter writer = new FileWriter(getFile());
        for (int i = 0; i < tasks.size(); i++) {
            writer.write(TaskList.taskToString(tasks.getTask(i)) + '\n');
        }
        writer.close();
    }
}