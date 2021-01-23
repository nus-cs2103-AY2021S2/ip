package duke;

import duke.task.Task;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    Path filePath;

    public Storage(String path) {
        this.filePath = Paths.get(path);
    }

    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        List<String> strings = Files.readAllLines(filePath);
        for (String line : strings) {
            Task task = Parser.parseInFile(line);
            tasks.add(task);
        }
        return tasks;
    }

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
