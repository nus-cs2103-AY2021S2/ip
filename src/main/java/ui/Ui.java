package ui;

import java.util.List;
import java.util.Scanner;

import duke.Task;
import duke.TaskList;
import dukeexception.DukeException;

public class Ui {
    private Scanner sc;
    private String userInput;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String getUserInput() {
        this.userInput = this.sc.nextLine().trim();
        return userInput;
    }

    /**
     * Method which prints a greeting message.
     */
    public static void printGreeting() {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Greetings! I am Duke! How may I assist you?\n");
    }

    /**
     * Method which prints goodbye message.
     */
    public static void printGoodbye() {
        System.out.println("\nGoodbye! Have a nice day!\n");
    }

    /**
     * Static method which prints the list of tasks.
     * @param tasks A task list containing list of tasks to be printed.
     * @throws DukeException Throws an exception if task list is empty.
     */
    public static void printList(TaskList tasks) throws DukeException {
        try {
            for (int i = 1; i <= tasks.getSize(); i++) {
                System.out.println(i + ". " + tasks.get(i).toString());
            }
        } catch (Exception e) {
            throw new DukeException("There is 0 task in the list.");
        }
    }

    /**
     * Method which prints a filtered list with a specific filter.
     * @param tasks List of filtered tasks to be printed.
     */
    public void printFilteredList(List<Task> tasks) {

        if (!tasks.isEmpty()) {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(tasks.get(i).toString());
            }
        } else {
            System.out.println("Sorry, there is no task with matching keywords.");
        }
    }

    /**
     * Method which prints done message for the task that has been marked done.
     * @param task
     */
    public void printDoneMessage(Task task) {
        System.out.println("Alright, I will mark this as done.\n" + task.toString());
    }

    /**
     * Method which prints A task added prompt to confirm task is been added to the list.
     * @param task The task that is added to the list.
     * @param count The number of tasks in the current task list.
     */
    public void printTaskAddedMessage(Task task, int count) {
        System.out.println("Got it. I have added this task: \n" + task.getDescription()
                            + ". \n Now you have " + count + " items in your list.");
    }

    /**
     * Method which prints a task that is removed from the list.
     * @param index The index of corresponding task to be removed from the list.
     */
    public void printTaskRemovedMessage(int index) {
        System.out.println("I have removed item " + index + ". Welcome.");
    }

    /**
     * Method which prints a message when there is no task in the list.
     */
    public void printNoTaskMessage() {
        System.out.println("It seems like there's no such task in your list:(");
    }
}
