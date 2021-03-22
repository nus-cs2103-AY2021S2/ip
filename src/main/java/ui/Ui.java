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
    public String printGreeting() {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

        String merge1 = "Hello from\n" + logo + "\n" + "Greetings! I am duke.Duke! How may I assist you?\n";

        return merge1;
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
     * @return Response.
     * @throws DukeException Throws an exception if task list is empty.
     */
    public static String printList(TaskList tasks) throws DukeException {
        String res = "";
        if (tasks.getSize() == 0) {
            res = "It seems like there is no tasks in the list.";
        } else {
            for (int i = 1; i <= tasks.getSize(); i++) {
                res = res + i + ". " + tasks.get(i).toString() + "\n";
            }
        }
        return res;
    }

    /**
     * Method which prints a filtered list with a specific filter.
     * @param tasks List of filtered tasks to be printed.
     * @return Response.
     */
    public String printFilteredList(List<Task> tasks) {

        if (!tasks.isEmpty()) {
            String res = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < tasks.size(); i++) {
                res = res + tasks.get(i).toString() + "\n";
            }
            return res;
        } else {
            return "Sorry, there is no task with matching keywords.";
        }
    }

    /**
     * Method which prints done message for the task that has been marked done.
     * @param task This task.
     * @return Response.
     */
    public String printDoneMessage(Task task) {
        return "Alright, I will mark this as done.\n" + task.toString();
    }

    /**
     * Method which prints A task added prompt to confirm task is been added to the list.
     * @param task The task that is added to the list.
     * @param count The number of tasks in the current task list.
     * @return Response.
     */
    public String printTaskAddedMessage(Task task, int count) {
        return "Got it. I have added this task: \n" + task.getDescription()
                            + ". \n Now you have " + count + " items in your list.";

    }

    /**
     * Method which prints a task that is removed from the list.
     * @param index The index of corresponding task to be removed from the list.
     * @return Response.
     */
    public String printTaskRemovedMessage(int index) {
        return "I have removed item " + index + ". Welcome.";
    }

    /**
     * Method which prints a message when there is no task in the list.
     * @return Response.
     */
    public String printNoTaskMessage() {
        return "It seems like there's no such task in your list:(";
    }

    /**
     * Method which prints sample input format for user.
     */
    public static void printHelpMessage() {
        System.out.println("*todo task*              ===> todo something");
        System.out.println("*delete task at index*   ===> delete 1");
        System.out.println("*list tasks*             ===> list");
        System.out.println("*deadline task*          ===> dateline name /by 2011-1-23");
        System.out.println("*event task*             ===> event name /at 2011-1-23");
    }
}
