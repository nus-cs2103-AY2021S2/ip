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

public class Storage {
    Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

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

    public List<Task> load() throws IOException {
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
