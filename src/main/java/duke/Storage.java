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
    private final File f;

    private final String path = "/Users/rachel/Desktop/ip/src/main/java/duke/data/myDuke.txt";

    /**
     * initialises a storage object.
     *
     * @throws DukeException due to IOException.
     */
    public Storage() throws DukeException {
        this.f = new File(path);
        createFile();
    }

    /**
     * creates a new file if not found in directory.
     *
     * @throws DukeException due to IOException.
     */
    public void createFile() throws DukeException {
        try {
            if (f.getParentFile().mkdirs()) {
                System.out.println("File is loading...");
            } else {
                f.createNewFile();

                System.out.println("File already exists!");
            }
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! File creation error");
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
            Scanner s = new Scanner(f);
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
        Task t = null;
        try {
            LocalDateTime dateTime;
            String type = description[0];
            switch (type) {
            case "T":
                t = new ToDo(description[2]);
                break;

            case "D":
                dateTime = LocalDateTime.parse(description[3]);
                t = new Deadline(description[2], dateTime);
                break;

            case "E":
                dateTime = LocalDateTime.parse(description[3]);
                t = new Event(description[2], dateTime);
                break;
            default:
            }

            if (description[1].equals("1")) {
                assert t != null;
                t.markAsDone();
            }

        } catch (Exception e) {

            throw new DukeException(
                    "☹ OOPS!!! I do not understand what are you saying!");

        }

        return t;
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
