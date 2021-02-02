import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private File file;

    public Storage(String pathname) {
        this.file = new File(pathname);
    }

    public List<Task> loadTasksFromFile() throws DukeException {
        List<Task> taskList = new ArrayList<>();
        try {
            if (!this.file.exists()) {
                file.getParentFile().mkdir();
                file.createNewFile();
                return taskList;
            }
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }

        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] taskData = line.split("\\|");
                String taskType = taskData[0];
                String taskStatus = taskData[1];
                String taskDescription = taskData[2];
                Task task = null;

                switch(taskType) {
                case("T"):
                    task = new Todo(taskDescription);
                    break;
                case("D"):
                    task = new Deadline(taskDescription, taskData[3]);
                    break;
                case("E"):
                    task = new Event(taskDescription, taskData[3]);
                    break;
                }

                if (task != null && taskStatus.equals("1")) {
                    task.setDone(true);
                }

                taskList.add(task);
            }

        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        return taskList;
    }

    public void saveTasksToFile(TaskList tasks) throws DukeException {
        StringBuilder taskString = new StringBuilder();
        tasks.forEach(task -> taskString.append(task.toSaveFormat()));

        try {
            if (!file.exists()) {
                file.getParentFile().mkdir();
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }

        try (BufferedWriter writer = Files.newBufferedWriter(file.toPath())) {
            writer.write(taskString.toString());
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
