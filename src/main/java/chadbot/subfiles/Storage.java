package chadbot.subfiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import chadbot.exceptions.DateFormatException;
import chadbot.exceptions.LoadFailureException;
import chadbot.task.Deadline;
import chadbot.task.Event;
import chadbot.task.Task;
import chadbot.task.ToDo;

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
        assert(filenames != null);

        this.path = filenames[0];
        this.filename = filenames[1];
    }

    /**
     * Loads the data stored in the save file.
     *
     * @param taskList The list of tasks that the user has in the current execution of the Duke program.
     */
    public void loadData(TaskList taskList) {
        File pathToFile = new File(path);
        if (!pathToFile.exists()) {
            pathToFile.mkdir();
        }

        File file = new File(path + filename);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                taskList.addTaskFromData(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.out.println("Something went wrong during the creation of your save file.");
            }
        } catch (DateFormatException e) {
            System.out.println("There is an error with the format of a date in your save file.");
        } catch (LoadFailureException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves the list of tasks that the user currently has in the save file.
     *
     * @param taskList The list of tasks that the user has in the current execution of the Duke program.
     */
    public void saveData(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(path + filename);
            ArrayList<Task> tasks = taskList.getTasks();

            for (Task t : tasks) {
                if (t instanceof ToDo) {
                    String writeData = "T | " + (t.isDone() ? 1 : 0) + " | " + t.getName();
                    fw.write(writeData);
                } else if (t instanceof Deadline) {
                    Deadline d = (Deadline) t;
                    String writeData = "D | " + (d.isDone() ? 1 : 0) + " | " + d.getName() + " | " + d.getDate();
                    fw.write(writeData);
                } else if (t instanceof Event) {
                    Event e = (Event) t;
                    String writeData = "E | " + (e.isDone() ? 1 : 0) + " | " + e.getName() + " | " + e.getDate();
                    fw.write(writeData);
                }

                fw.write(System.lineSeparator());
            }

            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong during the saving of your file.");
        }
    }

}
