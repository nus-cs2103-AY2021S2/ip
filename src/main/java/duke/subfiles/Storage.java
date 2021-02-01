package duke.subfiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DateFormatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * The Storage class manages the loading and storing of tasks.
 *
 * @author  arsatis
 * @version 1.1
 * @since   2021-01-26
 */
public class Storage {
    /** The path leading to the save file. */
    private String path;

    /** The name of the save file. */
    private String filename;

    /**
     * Default constructor for the Storage class.
     *
     * @param filenames The path leading to and the name of the save file.
     */
    @SafeVarargs
    public Storage(String... filenames) {
        this.path = filenames[0];
        this.filename = filenames[1];
    }

    /**
     * Loads the data stored in the save file.
     *
     * @param taskList The list of tasks that the user has in
     *                 the current execution of the Duke program.
     */
    public void loadData(TaskList taskList) {
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }

        f = new File(path + filename);
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                taskList.addTaskFromData(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            try {
                f.createNewFile();
            } catch (IOException ex) {
                System.out.println("Something went wrong during the creation of your save file.");
            }
        } catch (DateFormatException e) {
            System.out.println("There is an error with the format of a date in your save file.");
        }
    }

    /**
     * Saves the list of tasks that the user currently has in
     * the save file.
     *
     * @param taskList The list of tasks that the user has in
     *                 the current execution of the Duke program.
     */
    public void saveData(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(path + filename);
            ArrayList<Task> tasks = taskList.getTasks();

            for (Task t : tasks) {
                if (t instanceof ToDo) {
                    fw.write("T | " + (t.isDone() ? 1 : 0)
                            + " | " + t.getName());
                } else if (t instanceof Deadline) {
                    Deadline d = (Deadline) t;
                    fw.write("D | " + (d.isDone() ? 1 : 0) + " | "
                            + d.getName() + " | " + d.getDate());
                } else {
                    Event e = (Event) t;
                    fw.write("E | " + (e.isDone() ? 1 : 0) + " | "
                            + e.getName() + " | " + e.getDate());
                }
                fw.write(System.lineSeparator());
            }

            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong during the saving of your file.");
        }
    }

}
