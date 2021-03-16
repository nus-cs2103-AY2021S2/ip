package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import task.Events;
import task.Task;
import task.TaskList;
import task.Todo;

public class Storage {

    private String directory;
    private String fileName;
    /**
     * Initialises a Storage object.
     * @param directory
     * @param fileName
     * @return a Storage object
     */
    public Storage(String directory, String fileName) {
        this.directory = directory;
        this.fileName = fileName;
    }

    /**
     * Saves the tasklist.
     * Converts a tasklist into its string representation
     * then saves it in a file
     *
     * @param taskList the tasklist to be saved
     */
    public void saveToHardisk(TaskList taskList) {

        try {
            PrintWriter writer = new PrintWriter(this.directory
                    + this.fileName, "UTF-8");

            for (Task task : taskList.getList()) {
                writer.println(task.tokenize());
            }

            writer.close();

        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    /**
     * Loads a text file and parses it into a tasklist.
     */
    public TaskList loadFromHardisk() {

        try {
            File file = new File(this.directory + this.fileName);
            Scanner sc = new Scanner(file);

            TaskList taskList = new TaskList();

            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                Task task = parseData(data);

                if (task != null) {
                    taskList.add(task);
                }
            }
            return taskList;

        } catch (FileNotFoundException e) {
            createNewFile();
            return new TaskList();
        }
    }

    /**
     * Parses a string representation of a task into a Task object.
     *
     * @param data  String representation of task
     * @return      the Task parsed
     */
    private Task parseData(String data) {
        String[] tokens = data.split("\\|", 2);
        String taskSymbol = tokens[0];
        String taskDetails = tokens[1];

        switch (taskSymbol) {
        case "T":
            return createTodo(taskDetails);
        case "D":
            return createDeadline(taskDetails);
        case "E":
            return createEvent(taskDetails);
        default:
            return null;
        }
    }

    /**
     * Creates an Event object from the Task details.
     *
     * @param taskDetails   Task details of the event object
     * @return              The Event Object specified
     */
    private Task createEvent(String taskDetails) {
        String[] tokens = taskDetails.split("\\|");
        boolean isDone = !tokens[0].equals("0");

        String details = tokens[1];
        String at = tokens[2].equals("NULL") ? null : tokens[2];
        Integer three = 3;
        String tag = tokens[three].equals("NULL") ? null : tokens[3];

        return new Events(isDone, details, at, tag);
    }

    /**
     * Creates a Deadline object from the Task details.
     *
     * @param taskDetails   Task details of the deadline object
     * @return              The Deadline Object specified
     */
    private Task createDeadline(String taskDetails) {
        String[] tokens = taskDetails.split("\\|");
        boolean isDone = !tokens[0].equals("0");

        String details = tokens[1];
        String by = tokens[2].equals("NULL") ? null : tokens[2];
        String tag = tokens[3].equals("NULL") ? null : tokens[3];

        return new Events(isDone, details, by, tag);
    }
    /**
     * Creates a Todo object from the Task details.
     *
     * @param taskDetails   Task details of the todo object
     * @return              The Todo Object specified
     */
    private Task createTodo(String taskDetails) {
        String[] tokens = taskDetails.split("\\|");
        boolean isDone = !tokens[0].equals("0");
        String details = tokens[1];
        String tag = tokens[2].equals("NULL") ? null : tokens[2];

        return new Todo(isDone, details, tag);
    }

    /**
     * Creates a new file and directory.
     */
    private void createNewFile() {
        File directory = new File(this.directory);
        if (!directory.exists()) {
            directory.mkdir();
        }

        try {
            File file = new File(this.directory + this.fileName);
            if (file.createNewFile()) {
                System.out.println("Created File: " + this.directory
                        + this.fileName);
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
