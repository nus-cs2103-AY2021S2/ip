package duke.storage;

import duke.parser.Parser;
import duke.exception.DukeException;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

public class Storage {
    private File file;

    private final String SAVE_FORMAT_DELIMITER = " \\| ";

    /**
     * Initializes a Storage object with the default path.
     */
    public Storage() {
        file = new File("data", "duke.txt");
    }

    /**
     * Creates directory and file if not found.
     */
    public void createFile() throws DukeException {
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
                System.out.println("Directory successfully created");
            }
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File successfully created");
            }
        } catch (IOException e) {
            throw new DukeException(
                    "There is an error while creating file " + file.getName());
        }
    }

    /**
     * Loads data from save file into Duke during startup.
     *
     * @param tasks TaskList object used to store Tasks
     */
    public void loadTasksFromFile(TaskList tasks) throws DukeException {
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

    /**
     * Parses a string of task and adds it to a TaskList object.
     *
     * @param tasks      TaskList containing all tasks currently in Duke.
     * @param taskString String representing a task from file
     */
    public void parseTasksFromFile(TaskList tasks, String taskString) throws IndexOutOfBoundsException {
        String[] taskStringArr = taskString.split(SAVE_FORMAT_DELIMITER);
        switch (taskStringArr[0].trim()) {
        case "T":
            Todo todo = new Todo(taskStringArr[2]);
            addToTasks(tasks, todo, taskStringArr[1]);
            break;
        case "D":
            Deadline deadline = new Deadline(taskStringArr[2], Parser.parseDateTimeFromFile(taskStringArr[3]));
            addToTasks(tasks, deadline, taskStringArr[1]);
            break;
        case "E":
            Event event = new Event(taskStringArr[2], Parser.parseDateTimeFromFile(taskStringArr[3]));
            addToTasks(tasks, event, taskStringArr[1]);
            break;
        default:
            break;
        }
    }

    /**
     * Adds to a task to TaskList and updates its completion status.
     *
     * @param tasks  TaskList containing all tasks currently in Duke.
     * @param task   Task to be added to TaskList.
     * @param status String representation of the completion status of the task.
     */
    public void addToTasks(TaskList tasks, Task task, String status) {
        if (status.trim().equals("1")) {
            task.markAsDone();
        }
        tasks.addTask(task);
    }

    /**
     * Saves current list of tasks to file.
     *
     * @param tasks TaskList object containing all current tasks.
     */
    public void saveTasksToFile(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file);
            for (Task task : tasks.getAllTasks()) {
                fw.write(task.toSaveFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException(
                    "There is an error while writing to file " + file.getName());
        }
    }
}

