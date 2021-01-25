package main.java.subfiles;

import main.java.exceptions.DateFormatException;
import main.java.exceptions.EmptyDescriptionException;
import main.java.exceptions.EmptyTimeException;
import main.java.exceptions.InvalidInputException;
import main.java.exceptions.ListOutOfBoundsException;

public class Ui {
    private TaskList taskList;

    public Ui(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Greets the user upon execution of the program.
     */
    public void greet() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Bids the user farewell before termination of the program.
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printTasks(String s) {
        String[] sArray = s.split(" ");

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
    public void inputDone(String index) {
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
    public void inputDelete(String index) {
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
    public void inputAdd(String s) {
        try {
            taskList.addTask(s);
        } catch (EmptyDescriptionException | EmptyTimeException | InvalidInputException |
                DateFormatException e) {
            System.out.println(e.getMessage());
        }
    }

}
