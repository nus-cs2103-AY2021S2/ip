import models.Deadline;
import models.Event;
import models.Task;
import models.Todo;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

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

    public static String taskToString(Task task) {
        StringBuilder builder = new StringBuilder();
        if (task instanceof Deadline) {
            builder.append('D');
        } else if (task instanceof Event) {
            builder.append('E');
        } else if (task instanceof Todo) {
            builder.append('T');
        }

        builder.append("|");
        builder.append(task.getTaskDone() ? 1 : 0);

        builder.append("|");
        builder.append(task.getTaskName());

        if (task instanceof Deadline) {
            builder.append("|");
            builder.append(((Deadline) task).getDeadline());
        } else if (task instanceof Event) {
            builder.append("|");
            builder.append(((Event) task).getDate());
        }

        return builder.toString();
    }

    public static Task parseTask(String taskString) {
        String[] taskStringArray = taskString.split("\\|");
        String type = taskStringArray[0];
        Task task = null;

        if (type.equals("T")) {
            task = new Todo(taskStringArray[2]);
        } else if (type.equals("D")) {
            task = new Deadline(taskStringArray[2], LocalDateTime.parse(taskStringArray[3]));
        } else if (type.equals("E")) {
            task = new Event(taskStringArray[2], LocalDateTime.parse(taskStringArray[3]));
        }

        if (taskStringArray[1].equals('1') && task != null) {
            task.markAsDone();
        }

        return task;
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

    public ArrayList<Task> readTasks() throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(getFile()));
        String line;

        while ((line = reader.readLine()) != null) {
            taskList.add(parseTask(line));
        }

        return taskList;
    }

    public void writeTasks(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(getFile());
        for (Task task : tasks) {
            writer.write(taskToString(task) + '\n');
        }
        writer.close();
    }
}