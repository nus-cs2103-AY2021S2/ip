package main.java;

import java.util.Scanner;

import main.java.exceptions.DateFormatException;
import main.java.exceptions.EmptyDescriptionException;
import main.java.exceptions.EmptyTimeException;
import main.java.exceptions.InvalidInputException;
import main.java.exceptions.ListOutOfBoundsException;

import main.java.subfiles.Storage;
import main.java.subfiles.TaskList;

/**
 * The Duke program is an interactive application which
 * enables users to store and modify their tasks.
 *
 * @author  arsatis
 * @version 1.0
 * @since   2021-01-19
 */
public class Duke {
    private Storage storage;
    /** Task list which manages the tasks created by user input */
    private TaskList taskList;

    public Duke(String path, String filename) {
        storage = new Storage(path, filename);
        taskList = new TaskList();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean hasInput = true;

        greet();
        storage.loadData(taskList);
        while (hasInput) {
            String s = sc.nextLine();
            hasInput = executeInput(s);
            System.out.println();
        }
        storage.saveData(taskList);
        exit();

        sc.close();
    }

    /**
     * Greets the user upon execution of the program.
     */
    private void greet() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Bids the user farewell before termination of the program.
     */
    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void printTasks(String[] sArray) {
        if (sArray.length == 1) {
            taskList.printTasks();
        } else {
            try {
                taskList.printTasksOnDate(sArray[1]);
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
    private void inputDone(String index) {
        try {
            taskList.markDone(index);
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
    private void inputDelete(String index) {
        try {
            taskList.deleteTask(index);
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
    private void inputAdd(String s) {
        try {
            taskList.addTask(s);
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
    private boolean executeInput(String s) {
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

    /**
     * The main method which is executed when the Duke program
     * is run.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Duke("../src/main/java/data/", "duke.txt").run();
    }

}
