import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final String DEFAULT_FILEPATH = "duke.txt";

    private final Path path;

    public Storage() throws InvalidStorageFilePathException {
        this(DEFAULT_FILEPATH);
    }

    public Storage(String path) throws InvalidStorageFilePathException {
        if (!isValidFilePath(path)) {
            throw new InvalidStorageFilePathException("The file path of a storage file should end with '.txt'");
        }
        this.path = Path.of(path); // may throw InvalidPathException here?
    }

    private static boolean isValidFilePath(String filePath) {
        return filePath.endsWith(".txt");
    }

    public void saveTasks(TaskList taskList) throws StorageException {
        try {
            List<String> taskStrings = Storage.convertAllTasksToString(taskList);
            Files.write(path, taskStrings);
        } catch (IOException ex) {
            throw new StorageException("Error writing data to file: " + path);
        }
    }

    public TaskList loadTasks() throws IOException {
        TaskList taskList = new TaskList();
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            return taskList;
        }
        List<String> taskStrings = Files.readAllLines(path);  
        for (String s : taskStrings) {
            taskList.addTask(Storage.convertStringToTask(s));
        }
        return taskList;
    }

    public static Task convertStringToTask(String taskString) {
        Task task = null;
        String[] arr = taskString.split("\\s\\|\\s");
        String taskType = arr[0];
        String taskStatus = arr[1];
        String taskName = arr[2];
        boolean isTaskCompleted = taskStatus.equals("1");
        if (taskType.equals(ToDoTask.IDENTIFIER)) {
            task = new ToDoTask(taskName, isTaskCompleted);
        } else if (taskType.equals(DeadlineTask.IDENTIFIER)) {
            String taskDescription = arr[3];
            LocalDateTime deadline = LocalDateTime.parse(taskDescription, Formatter.OUTPUT_DATE_FORMATTER);
            task = new DeadlineTask(taskName, isTaskCompleted, deadline);
        } else if (taskType.equals(EventTask.IDENTIFIER)) {
            String taskDescription = arr[3];
            task = new EventTask(taskName, isTaskCompleted, taskDescription);
        }
        return task;
    }

    public static List<String> convertAllTasksToString(TaskList taskList) {
        List<String> taskStrings = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.getTask(i);
            taskStrings.add(Storage.convertTaskToString(task));
        }
        return taskStrings;
    }
    
    public static String convertTaskToString(Task task) {
        // String format: "<taskType> | <taskStatus> | <taskName>", [" | <additionalInfo>]"]
        StringBuilder encodedTaskString = new StringBuilder();
        encodedTaskString.append(task.getTaskType());
        encodedTaskString.append(" | ");
        encodedTaskString.append(task.isDone() ? "1" : "0");
        encodedTaskString.append(" | ");
        encodedTaskString.append(task.getName());
        if (task.getTaskType().equals("D")) {
            DeadlineTask deadlineTask = (DeadlineTask) task;
            encodedTaskString.append(" | ");
            encodedTaskString.append(deadlineTask.getDeadline());
        } else if (task.getTaskType().equals("E")) {
            EventTask eventTask = (EventTask) task;
            encodedTaskString.append(" | ");
            encodedTaskString.append(eventTask.getEventTime());
        }
        return encodedTaskString.toString();
    }
}
