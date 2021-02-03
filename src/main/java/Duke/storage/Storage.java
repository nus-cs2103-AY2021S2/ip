package duke.storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import duke.parser.ListParser;
import duke.task.Task;
import duke.task.TaskList;


public class Storage {
    private static Path filePath;

    /**
     * Retrieves file path of stored data.
     */
    public void retrieveFilePath() {
        try {
            String home = System.getProperty("user.home");
            Path directoryPath = Paths.get(home.toString(), "Desktop", "repo", "ip", "data");
            Boolean directoryExists = Files.exists(directoryPath);
            if (directoryExists.equals(false)) {
                Files.createDirectory(directoryPath);
            }
            Path file = Paths.get(directoryPath.toString(), "Baron.txt");
            Boolean fileExists = Files.exists(file);
            if (fileExists.equals(false)) {
                Files.createFile(file);
            }
            filePath = file;
        } catch (IOException e) {
            System.out.println("directory/file is already created.");
        }
    }

    /**
     * Saves taskList into Storage.
     *
     * @param taskList
     */
    public void save(ArrayList<Task> taskList) {
        try {
            String data = "";

            for (int i = 0; i < taskList.size(); i++) {
                data = data + taskList.get(i).encode() + "\n";
            }
            Files.write(filePath, data.getBytes(StandardCharsets.UTF_8));

        } catch (IOException e) {
            System.out.println("save error");

        }
    }

    /**
     * Loads taskList from text file into Storage.
     *
     * @return TaskList
     * @throws IOException
     */
    public TaskList load() throws IOException {
        TaskList taskList = new TaskList();
        retrieveFilePath();
        List<String> s = Files.readAllLines(filePath);

        for (int i = 0; i < s.size(); i++) {
            ListParser p = new ListParser();
            p = p.parse(s.get(i));
            taskList.populate(p);
        }
        return taskList;
    }
}
