package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

public class Storage {

    private final String path = "./data/myDuke.txt";

    /**
     * initialises a storage object.
     *
     * @throws DukeException due to IOException.
     */
    public Storage() throws DukeException {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } else {
                displayTasks();
            }
        } catch (IOException ex) {
            System.out.println("failed to create directory");
        }
    }

    /**
     * Returns a list of tasks in the myDuke.txt file.
     *
     * @return a list of tasks.
     * @throws DukeException due to FileNotFoundException.
     */
    public ArrayList<Task> displayTasks() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(path);
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                Task task = parseTasks(s.nextLine());
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("☹ OOPS!!! File not found!");
        }
        return tasks;
    }

    /**
     * Parses the task saved in the file to identify
     * the type of task.
     *
     * @param task description of task in String.
     * @return a newly created Task.
     * @throws DukeException if the type of task is unknown.
     */
    public Task parseTasks(String task) throws DukeException {
        String[] description = task.split(" / ", 4);
        Task myTask = null;
        try {
            LocalDateTime dateTime;
            String type = description[0];
            switch (type) {
            case "T":
                myTask = new ToDo(description[2]);
                break;

            case "D":
                dateTime = LocalDateTime.parse(description[3]);
                myTask = new Deadline(description[2], dateTime);
                break;

            case "E":
                dateTime = LocalDateTime.parse(description[3]);
                myTask = new Event(description[2], dateTime);
                break;
            default:
            }

            if (description[1].equals("1")) {
                assert myTask != null;
                myTask.markAsDone();
            }

        } catch (Exception e) {

            throw new DukeException(
                    "☹ OOPS!!! I do not understand what are you saying!");

        }

        return myTask;
    }

    /**
     * Saves tasks in the myDuke.txt file
     *
     * @param tasks A list of tasks
     * @throws DukeException due to IOException.
     */
    public void saveTasks(ArrayList<Task> tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(path);
            for (Task t : tasks) {
                fw.write(t.toSave() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! Tasks cannot be saved.");
        }
    }
}
