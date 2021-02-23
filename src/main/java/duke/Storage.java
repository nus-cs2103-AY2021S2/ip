package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exceptions.InvalidFileDataException;
import duke.exceptions.InvalidFolderException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * <code>Storage</code> class handles the loading of
 * tasks from the file and saving tasks in the file.
 */
public class Storage {
    private static final String filePath = "./data/duke.txt";

    /**
     * Constructor for Storage class.
     * Initializes File for the task file.
     *
     * @throws InvalidFolderException If folder to store the file does not exist.
     */
    public Storage() throws InvalidFolderException {
        try {
            File file = new File(filePath);

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } else {
                readFileContents();
            }
        } catch (IOException ex) {
            throw new InvalidFolderException();
        } catch (InvalidFileDataException ex) {
            ex.getMessage();
        }
    }

    /**
     * Returns a List of existing tasks from the file when Duke starts up.
     *
     * @return List of existing tasks in the file.
     * @throws FileNotFoundException If the file cannot be found.
     */
    public List<Task> readFileContents()
            throws FileNotFoundException, InvalidFileDataException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        List<Task> list = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String taskString = scanner.nextLine();
            String[] splitString = taskString.split(" \\| ");
            Task task;

            String taskType = splitString[0];
            String taskDone = splitString[1];
            String taskDescription = splitString[2] + " ";
            LocalDateTime taskDate;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

            switch (taskType) {
            case "T":
                task = new ToDo(taskDescription);
                break;

            case "D":
                taskDate = LocalDateTime.parse(splitString[3], formatter);
                task = new Deadline(taskDescription, taskDate);
                break;

            case "E":
                taskDate = LocalDateTime.parse(splitString[3], formatter);
                task = new Event(taskDescription, taskDate);
                break;

            default:
                throw new InvalidFileDataException();
            }

            if (taskDone.equals("1")) {
                task.markAsDone();
            }
            list.add(task);
        }
        return list;
    }

    /**
     * Saves the tasks in the file whenever the task list changes.
     *
     * @param list List of existing tasks.
     */
    public void overWriteFile(List<Task> list) {
        try {
            FileWriter fw = new FileWriter(filePath);

            for (Task task : list) {
                boolean done = task.isDone();
                String doneString = "0";
                String taskDescription = task.getDescription();
                String taskDate;

                if (done) {
                    doneString = "1";
                }

                if (task.getTaskType().equals("ToDo")) {
                    fw.write("T | " + doneString
                            + " | " + taskDescription + "\n");
                } else {
                    taskDate = task.getTaskDate();

                    if (task.getTaskType().equals("Deadline")) {
                        fw.write("D | " + doneString
                                + " | " + taskDescription + "| " + taskDate + "\n");
                    } else {
                        fw.write("E | " + doneString
                                + " | " + taskDescription + "| " + taskDate + "\n");
                    }
                }
            }
            fw.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
