import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;
import java.util.Arrays;

import java.util.Scanner;

public class DataManager {
    private File file;

    private final String SAVE_FORMAT_DELIMITER = " \\| ";

    DataManager() {
        file = new File("data", "duke.txt");
    }

    public void createFile() throws DukeException {
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
                System.out.println("CREATED_FOLDER");
            }
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("CREATED_SAVE_FILE");
            }
        } catch (IOException e) {
            throw new DukeException(
                    "There is an error while creating file " + file.getName());
        }
    }

    public void loadTasksFromFile(List<Task> tasks) throws DukeException {
        createFile();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                parseTasksFromFile(tasks, sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new DukeException(
                    "There is an error while loading file " + file.getName());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(
                    "There is an error while reading file " + file.getName());
        }
    }

    public void parseTasksFromFile(List<Task> tasks, String taskString) throws IndexOutOfBoundsException {
        String[] taskStringArr = taskString.split(SAVE_FORMAT_DELIMITER);
        switch (taskStringArr[0].trim()) {
            case "T":
                Todo todo = new Todo(taskStringArr[2]);
                addToTasks(tasks, todo, taskStringArr[1]);
                break;
            case "D":
                Deadline deadline = new Deadline(taskStringArr[2], taskStringArr[3]);
                addToTasks(tasks, deadline, taskStringArr[1]);
                break;
            case "E":
                Event event = new Event(taskStringArr[2], taskStringArr[3]);
                addToTasks(tasks, event, taskStringArr[1]);
                break;
            default:
                break;
        }
    }

    public void addToTasks(List<Task> tasks, Task task, String status) {
        if (status.trim().equals("1")) {
            task.markAsDone();
        }
        tasks.add(task);
    }

    public void saveTasksToFile(List<Task> tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file);
            for (Task task : tasks) {
                fw.write(task.savedFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException(
                    "There is an error while writing to file " + file.getName());
        }
    }
}