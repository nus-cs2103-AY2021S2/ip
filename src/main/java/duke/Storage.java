package duke;

import java.time.LocalDate;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String DEFAULT_PATH = "./duke.txt";
    private final Path filePath;

    public Storage() {
        this.filePath = Paths.get(DEFAULT_PATH);
        File file = new File(DEFAULT_PATH);
        if (Files.notExists(this.filePath)) {
            try {
                System.out.println("created new file");
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Storage(String filePathStr) {
        this.filePath = Paths.get(filePathStr);
        File file = new File(DEFAULT_PATH);
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
    public void writeData(List<Task> taskList) throws IOException {
        FileWriter file = new FileWriter(DEFAULT_PATH);
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
                    // System.out.println("im at todo");
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
    public void writeData(TaskList taskList) throws IOException {
        writeData(taskList.getTaskList());
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
                    taskToBeAdded = new Deadline(taskDescription, LocalDate.parse(storedTask[3]), isDone);
                    break;
                case "TODO":
                    taskToBeAdded = new ToDo(taskDescription, isDone);
                    break;
                case "EVENT":
                    taskToBeAdded = new Event(taskDescription, LocalDate.parse(storedTask[3]), isDone);
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
