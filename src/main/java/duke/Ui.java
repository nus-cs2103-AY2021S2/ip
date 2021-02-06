package duke;

import java.util.List;

import duke.tasks.Task;


/**
 * UI class that is responsible for reading user input and printing
 */
public class Ui {
    /**
     * Constructor to initialize the UI Class
     */
    public Ui() {
    }
    /**
     * Prints the welcome message.
     */
    public static String printWelcome() {
        String introduction = "     Hello! I'm Duke\n" + "     What can I do for you?\n";
        return introduction;
    }

    /**
     * Prints the goodbye message.
     */
    public static String printBye() {
        String farewell = "     Bye. Hope to see you again soon!\n";
        return farewell;
    }

    /**
     * Prints out a task.
     *
     * @param task  A task.
     */
    public String printTask(Task task) {
        return task.toString() + "\n";
        //System.out.println(task);
    }

    /**
     * Prints out the string input
     *
     * @param str A string
     */
    public String print(String str) {
        return str;
    }

    /**
     * Prints the message when a task is added.
     */
    public String addPrint() {
        String printAddTask = "     Got it. I've added this task: \n";
        return printAddTask;
    }

    /**
     * Prints the error when attempting to load file message.
     */
    public String showLoadingError() {
        String unableToLoad = "     Unable to load file. Creating new one\n";
        return unableToLoad;
    }

    /**
     * Prints out the number of tasks inside the TaskList
     *
     * @param list Tasklist
     */
    public String countTasks(TaskList list) {
        String countTasksMessage = "     Now you have " + list.getList().size() + " tasks in the list. \n";
        return countTasksMessage;
    }

    /**
     * Prints the message when a task is removed.
     */
    public static String printRemoved() {

        String removeTasksMessage = "     Noted. I've removed this task: \n";
        return removeTasksMessage;
    }

    /**
     * Prints the message when a task is marked.
     */
    public static String printMarked() {

        String taskMarkedMessage = "     Nice! I've marked this task as done:\n";
        return taskMarkedMessage;
    }
    /**
     * @param list the Task list to be printed
     * Prints out the current task list
     */
    public String printList(List<Task> list) {
        String printedList = "";
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            printedList += "      " + (i + 1) + "." + task.toString().trim() + "\n";
        }
        return printedList;
    }
    /**
     * Prints out the number of tasks inside the TaskList on list command.
     *
     * @param list The tasklist.
     */
    public String printStored(TaskList list) {
        List<Task> taskList = list.getList();

        String taskListMessage = "     Here are the tasks in your list:\n";

        return taskListMessage + this.printList(taskList);
    }
    /**
     * Prints out the matching message when user is finding for a keyword in the list
     */
    public String printMatching() {
        String printMatchingMessage = "     Here are the matching tasks in your list: \n";
        return printMatchingMessage;
    }
}
