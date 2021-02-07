package duke.ui;

import duke.taskclass.Task;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Handler for all UI displays of Duke.java
 */
public class ConsoleUI {
    private final Scanner in;

    /**
     * Constructor
     *
     * @param in system.in is expected here
     */
    public ConsoleUI(InputStream in) {
        this.in = new Scanner(in);
    }

    /**
     * Duke introduces itself
     */
    public void introduction() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String introduction = "I'm Duke!\nWhat can I do for ya?\n";
        formatBox(introduction);
    }

    /**
     * Duke says bye
     */
    public String bye() {
        return this.formatBox("Bye. Hope to see you again soon!");
    }

    /**
     * Duke speaks in chat boxes
     *
     * @param str input string within chat boxes
     */
    public String formatBox(String str) {
        return "-----------------------------------\n"
                + str + "\n-----------------------------------\n";
    }

    /**
     * Message shown on UI when a task is marked as done
     *
     * @param doneTask returns a message in String and Task.toString()
     */
    public String markDone(String doneTask) {
        return this.formatBox("Nice! I've marked this task as done:\n" + doneTask);
    }

    /**
     * Formatter for addTaskMessage and deleteTaskMessage
     */
    public String changeTaskMessage(String headerMessage, String changedTask, int numTask) {
        String formattedInput = headerMessage;
        formattedInput = formattedInput.concat(changedTask).concat("\n");
        formattedInput = formattedInput.concat("Now you have " + numTask + " tasks in the list.");
        return this.formatBox(formattedInput);
    }

    /**
     * Message shown on UI when a new task is added
     * Uses changeTaskMessage() to format text
     *
     * @param addedTask Task.toString() of the added task
     * @param numTask   the number of tasks currently in list of tasks
     */
    public String addTaskMessage(String addedTask, int numTask) {
        String formattedInput = "Got it. I've added this task:\n  ";
        return this.changeTaskMessage(formattedInput, addedTask, numTask);
    }

    /**
     * Message shown on UI when a task is deleted
     * Uses changeTaskMessage() to format text
     *
     * @param deletedTask Task.toString() of the deleted task
     * @param numTask     the number of tasks currently in list of tasks
     */
    public String deleteTaskMessage(String deletedTask, int numTask) {
        String formattedInput = "Got it. I've removed this task:\n  ";
        return this.changeTaskMessage(formattedInput, deletedTask, numTask);
    }

    /**
     * Prints the tasks in the array of tasks
     *
     * @param taskArr array of Task objects
     */
    public String list(Task[] taskArr) {
        String output = "++++++++++++++++++++++++++++++++++++\n" +
                "Here are the tasks in your list: \n" +
                "TaskType | isDone | taskName | time (if any)\n";
        /*
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        System.out.println("Here are the tasks in your list: ");
        System.out.println("TaskType | isDone | taskName | time (if any)");
        */
        for (Task t : taskArr) {
            if (t == null) {
                break;
            }
            // System.out.println(t.toString());
            output = output + t.toString() + "\n";
        }
        // System.out.println("+++++++++++++++++++++++++++++++++++++");
        output = output + "++++++++++++++++++++++++++++++++++++\n";

        return output;
    }

    /**
     * Reads the next line of input from sys.in
     *
     * @return line of input in String
     */
    public String nextLine() {
        return in.nextLine();
    }
}
