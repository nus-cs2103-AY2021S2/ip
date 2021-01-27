package duke;

import duke.task.Task;

import java.util.List;

/** An object that handles the printing of chat bot's messages*/
public class Ui {
    /** Borderlines to contain a display message*/
    private static final String BORDER = "___________________________________________________________";

    /**
     * Displays a message
     *
     * @param msg Message to be displayed
     */
    public void printMsg(String msg) {
        System.out.println(BORDER);
        System.out.println(msg);
        System.out.println(BORDER + "\n");
    }

    /**
     * Displays a default welcome message
     *
     * @param botName Name of the chat bot to greet with
     */
    public void printWelcomeMsg(String botName) {
        printMsg(String.format("Meow, I'm %s\nWhat can I do for you today?", botName));
    }

    /** Displays a default exit message */
    public void printGoodbyeMsg() {
        printMsg("Meow. Hope to see you again soon!");
    }

    /**
     * Displays a message to indicate the addition of a new task
     *
     * @param task Task that was added
     * @param tasksSize Number of total tasks after adding that new task
     */
    public void printAddMsg(Task task, int tasksSize) {
        System.out.println(BORDER);
        System.out.println("Got it meow. I've added this task:");
        System.out.printf("  [%s][%s] %s\n", task.getTypeSymbol(), task.getStatusSymbol(), task.getDesc());
        System.out.printf("Now you have %d tasks in the list.\n", tasksSize);
        System.out.println(BORDER + "\n");
    }

    /**
     * Displays a message to indicate the completion of a task
     *
     * @param index Index of the completed task in the list
     * @param task Task that was completed
     */
    public void printDoneMsg(int index, Task task) {
        System.out.println(BORDER);
        System.out.println("Good job meow, I've marked this task as done:");
        System.out.printf("%d.[%s][%s] %s\n", index + 1, task.getTypeSymbol(), task.getStatusSymbol(),
                task.getDesc());
        System.out.println(BORDER + "\n");
    }

    /**
     * Displays a message to indicate the deletion of a task
     *
     * @param task Task that was deleted
     * @param tasksSize Number of total tasks left after deleting that task
     */
    public void printDeleteMsg(Task task, int tasksSize) {
        System.out.println(BORDER);
        System.out.println("Noted meow. I've removed this task:");
        System.out.printf("  [%s][%s] %s\n", task.getTypeSymbol(), task.getStatusSymbol(),
                task.getDesc());
        System.out.printf("Now you have %d tasks in the list.\n", tasksSize);
        System.out.println(BORDER + "\n");
    }

    /**
     * Display the tasks from the search result
     *
     * @param tasks A list of tasks as search results
     */
    public void printFoundMsg(List<Task> tasks) {
        System.out.println(BORDER);
        System.out.println("Meow, here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.printf("%d.[%s][%s] %s\n", i + 1, task.getTypeSymbol(), task.getStatusSymbol(), task.getDesc());
        }
        System.out.println(BORDER + "\n");
    }

    /**
     * Displays all tasks currently in the list
     *
     * @param tasks List of tasks
     */
    public void printTaskList(List<Task> tasks) {
        System.out.println(BORDER);
        System.out.println("Meow, here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.printf("%d.[%s][%s] %s\n", i + 1, task.getTypeSymbol(), task.getStatusSymbol(), task.getDesc());
        }
        System.out.println(BORDER + "\n");
    }

    /**
     * Displays an error message
     *
     * @param msg Error message
     */
    public void printError(String msg) {
        printMsg(String.format("ERROR MEOW! %s", msg));
    }
}
