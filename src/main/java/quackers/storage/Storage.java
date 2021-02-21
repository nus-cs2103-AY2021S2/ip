package quackers.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import quackers.QuackersException;
import quackers.tasklist.TaskList;
import quackers.parser.FileDataParser;
import quackers.task.Task;
import quackers.ui.Ui;

public class Storage {

    protected Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(System.getProperty("user.dir"), filePath);
    }

    public ArrayList<Task> load() throws QuackersException {
        try {
            List<String> lines;
            lines = Files.readAllLines(this.filePath);

            ArrayList<Task> taskList = FileDataParser.getTaskListData(lines);
            return taskList;
        } catch (IOException e) {
            this.forceCreateStorageDirectory();
            throw new QuackersException(Ui.getLoadTaskListFailure());
        }
    }

    public void forceCreateStorageDirectory() throws QuackersException {
        try {
            Files.createDirectories(this.filePath.toAbsolutePath().getParent());
        } catch (IOException e) {
            throw new QuackersException(Ui.getLoadTaskListFailure());
        }
    }

    public void save(TaskList taskList) throws QuackersException {
        try {
            StringBuilder sb = new StringBuilder();
            for (Task task : taskList.getList()) {
                sb.append(task);
                sb.append('\n');
            }
            Files.write(this.filePath, sb.toString().getBytes());
        } catch (IOException e) {
            this.forceCreateStorageDirectory();
            throw new QuackersException(Ui.getSaveTaskListFailure());
        }
    }
}
