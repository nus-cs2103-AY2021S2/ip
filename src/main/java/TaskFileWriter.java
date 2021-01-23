import models.Deadline;
import models.Event;
import models.Task;
import models.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TaskFileWriter {
    private File file;

    public TaskFileWriter(Path directoryPath) throws IOException {
        File directory = directoryPath.toFile();
        directory.mkdirs();
        File dataFile = new File(directory, "data.log");
        dataFile.createNewFile();
        file = dataFile;
    }

    public TaskFileWriter() throws IOException {
        this(Paths.get(".","data"));
    }

    public static String parseTask(Task task) {
        StringBuilder builder = new StringBuilder();
        if (task instanceof Deadline) {
            builder.append('D');
        } else if (task instanceof Event) {
            builder.append('E');
        } else if (task instanceof Todo) {
            builder.append('T');
        }

        builder.append(" | ");
        builder.append(task.getTaskDone() ? 1 : 0);

        builder.append(" | ");
        builder.append(task.getTaskName());

        if (task instanceof Deadline) {
            builder.append(" | ");
            builder.append(((Deadline) task).getDeadline());
        } else if (task instanceof Event) {
            builder.append(" | ");
            builder.append(((Event) task).getDate());
        }

        return builder.toString();
    }

    public void writeTasks(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(file);
        for (Task task: tasks) {
            writer.write(parseTask(task) + '\n');
        }
        writer.close();
    }

    public static void main(String[] args) {
        try {
            TaskFileWriter writer = new TaskFileWriter();
        } catch (IOException e) {
            System.out.println("Cannot create file");
        }
    }
}