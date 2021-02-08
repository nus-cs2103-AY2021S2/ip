package duke;

import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Storage class to save information to hard disk.
 */
public class Storage {
    private final Path filePath;
    private final String filePathStr;

    /**
     * Constructor that takes in filepath to data file
     * @param filePathStr string of the file path to the data file
     */
    public Storage(String filePathStr) {
        this.filePath = Paths.get(filePathStr);
        this.filePathStr = filePathStr;
        File file = new File(filePathStr);
        if (Files.notExists(this.filePath)) {
            try {
                System.out.println("created new file");
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method to save TaskList to hard disk.
     *
     * @param taskList
     * @throws IOException
     */
    public void saveData(List<Task> taskList) throws IOException {
        FileWriter file = new FileWriter(filePathStr);
        for (Task task : taskList) {
            String toBeSaved = "";
            switch (task.getTaskType()) {
                case "DEADLINE":
                    toBeSaved = String.format("%s|%s|%s|%s",
                            task.getTaskType(),
                            task.getIsDone(),
                            task.getTaskDescription(),
                            ((Deadline) task).getEndTime());
                    break;
                case "TODO":
                    toBeSaved = String.format("%s|%s|%s",
                            task.getTaskType(),
                            task.getIsDone(),
                            task.getTaskDescription());
                    break;
                case "EVENT":
                    toBeSaved = String.format("%s|%s|%s|%s",
                            task.getTaskType(),
                            task.getIsDone(),
                            task.getTaskDescription(),
                            ((Event) task).getEventTime());
                    break;
                default:
                    continue;
            }
            file.write(toBeSaved + "\n");
        }
        file.close();
    }


    /**
     * Wrapper method to accept TaskList instead of a List.
     *
     * @param taskList
     * @throws IOException
     */
    public void saveData(TaskList taskList) throws IOException {
        saveData(taskList.getTaskList());
    }


    public List<Task> loadData() throws IOException {
        // checks to see if a file is already supposed to be there
        Scanner sc = new Scanner(filePath.toFile());
        List<Task> taskList = new ArrayList<>();
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] storedTask = line.split("\\|");
            String taskType = storedTask[0];
            boolean isDone = storedTask[1].equals("true");
            String taskDescription = storedTask[2];
            Task taskToBeAdded = null;
            switch (taskType) {
                case "DEADLINE":
                    taskToBeAdded = new Deadline(taskDescription, LocalDateTime.parse(storedTask[3]), isDone);
                    break;
                case "TODO":
                    taskToBeAdded = new ToDo(taskDescription, isDone);
                    break;
                case "EVENT":
                    taskToBeAdded = new Event(taskDescription, LocalDateTime.parse(storedTask[3]), isDone);
                    break;
                default:
                    continue;
            }
            taskList.add(taskToBeAdded);
        }
        sc.close();
        return taskList;
    }
}
