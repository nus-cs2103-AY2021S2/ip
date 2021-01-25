package main.java;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

import main.java.exceptions.DateFormatException;
import main.java.exceptions.EmptyDescriptionException;
import main.java.exceptions.EmptyTimeException;
import main.java.exceptions.InvalidInputException;
import main.java.exceptions.ListOutOfBoundsException;

import main.java.subfiles.*;

/**
 * The Duke program is an interactive application which
 * enables users to store and modify their tasks.
 *
 * @author  arsatis
 * @version 1.0
 * @since   2021-01-19
 */
public class Duke {
    /** Task manager which manages the tasks created by user input */
    private static TaskManager taskManager = new TaskManager();

    /**
     * Greets the user upon execution of the program.
     */
    private static void greet() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Bids the user farewell before termination of the program.
     */
    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void printTasks(String[] sArray) {
        if (sArray.length == 1) {
            taskManager.printTasks();
        } else {
            try {
                taskManager.printTasksOnDate(sArray[1]);
            } catch (DateFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Calls the task manager to mark a specified task as done
     * upon receiving a user input that attempts to mark a task
     * as done.
     *
     * @param index Index of the task to be marked as done
     *              in the list of tasks.
     */
    private static void inputDone(String index) {
        try {
            taskManager.markDone(index);
        } catch (InvalidInputException | ListOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Calls the task manager to delete a specified task from
     * the list upon receiving a user input that attempts to
     * delete a task from the list.
     *
     * @param index Index of the task to be deleted
     *              in the list of tasks.
     */
    private static void inputDelete(String index) {
        try {
            taskManager.deleteTask(index);
        } catch (InvalidInputException | ListOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Calls the task manager to add a specified task to the
     * list upon receiving a user input that attempts to add
     * a task to the list.
     *
     * @param s The user input which demanded for a task to be
     *          added to the list.
     */
    private static void inputAdd(String s) {
        try {
            taskManager.addTask(s);
        } catch (EmptyDescriptionException | EmptyTimeException | InvalidInputException |
                DateFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Calls the task manager to perform either task addition,
     * deletion, printing, or marking as done, based on the
     * user input. Also terminates the program if a terminating
     * input is supplied by the user.
     *
     * @param s A user input.
     * @return True if the user input is not a terminating
     *         input, and false otherwise.
     */
    private static boolean executeInput(String s) {
        String[] sArray = s.split(" ");

        switch (sArray[0]) {
        case "bye":
            return false;
        case "list":
            printTasks(sArray);
            break;
        case "done":
            inputDone(sArray[1]);
            break;
        case "delete":
            inputDelete(sArray[1]);
            break;
        default:
            inputAdd(s);
            break;
        }
        return true;
    }

    private static void loadData(String path, String filename) {
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }

        f = new File(path + filename);
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                taskManager.addTaskFromData(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            try {
                f.createNewFile();
            } catch (IOException ex) {
                System.out.println("Something went wrong during the creation of your save file.");
            }
        }
    }

    private static void saveData(String path, String filename) {
        File f = new File(path + filename);
        try {
            FileWriter fw = new FileWriter(path + filename);
            ArrayList<Task> tasks = taskManager.getTasks();

            for (Task t : tasks) {
                if (t instanceof ToDo) {
                    fw.write("T | " + (t.isDone() ? 1 : 0) +
                            " | " + t.getName());
                } else if (t instanceof Deadline) {
                    Deadline d = (Deadline) t;
                    fw.write("D | " + (d.isDone() ? 1 : 0) + " | " +
                            d.getName() + " | " + d.getDate());
                } else {
                    Event e = (Event) t;
                    fw.write("E | " + (e.isDone() ? 1 : 0) + " | " +
                            e.getName() + " | " + e.getDate());
                }
                fw.write(System.lineSeparator());
            }

            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong during the saving of your file.");
        }
    }

    /**
     * The main method which is executed when the Duke program
     * is run.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        String PATH = "../src/main/java/data/";
        String FILENAME = "duke.txt";
        Scanner sc = new Scanner(System.in);
        boolean hasInput = true;

        greet();
        loadData(PATH, FILENAME);
        while (hasInput) {
            String s = sc.nextLine();
            hasInput = executeInput(s);
        }
        saveData(PATH, FILENAME);
        exit();

        sc.close();
    }

}
