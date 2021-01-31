package duke.bot;

import java.util.List;

import duke.task.Task;

/** An object that handles the printing of chat bot's messages*/
public class Ui {
    /** Borderlines to contain a display message*/
    private static final String BORDER = "___________________________________________________________";

    /**
     * Displays a message
     *
     * @param msg Message to be displayed
     */
    public String constructMsg(String msg) {
        return BORDER + "\n" + msg + "\n" + BORDER + "\n";
    }

    /**
     * Displays a default welcome message
     *
     * @param botName Name of the chat bot to greet with
     */
    public String constructWelcomeMsg(String botName) {
        return constructMsg(String.format("Meow, I'm %s\nWhat can I do for you today?", botName));
    }

    /** Displays a default exit message */
    public String constructGoodbyeMsg() {
        return constructMsg("Meow. Hope to see you again soon!");
    }

    /**
     * Displays a message to indicate the addition of a new task
     *
     * @param task Task that was added
     * @param tasksSize Number of total tasks after adding that new task
     */
    public String constructAddMsg(Task task, int tasksSize) {
        String msg = "Got it meow. I've added this task:\n" + String.format("  [%s][%s] %s\n", task.getTypeSymbol(),
                task.getStatusSymbol(), task.getDesc()) + String.format("Now you have %d tasks in the list.\n",
                tasksSize);

        return constructMsg(msg);
//        System.out.println(BORDER);
//        System.out.println("Got it meow. I've added this task:");
//        System.out.printf("  [%s][%s] %s\n", task.getTypeSymbol(), task.getStatusSymbol(), task.getDesc());
//        System.out.printf("Now you have %d tasks in the list.\n", tasksSize);
//        System.out.println(BORDER + "\n");
    }

    /**
     * Displays a message to indicate the completion of a task
     *
     * @param index Index of the completed task in the list
     * @param task Task that was completed
     */
    public String constructDoneMsg(int index, Task task) {
        String msg = "Good job meow, I've marked this task as done:\n" + String.format("%d.[%s][%s] %s\n", index + 1, task.getTypeSymbol(), task.getStatusSymbol(),
                task.getDesc());

        return constructMsg(msg);
//        System.out.println(BORDER);
//        System.out.println("Good job meow, I've marked this task as done:");
//        System.out.printf("%d.[%s][%s] %s\n", index + 1, task.getTypeSymbol(), task.getStatusSymbol(),
//                task.getDesc());
//        System.out.println(BORDER + "\n");
    }

    /**
     * Displays a message to indicate the deletion of a task
     *
     * @param task Task that was deleted
     * @param tasksSize Number of total tasks left after deleting that task
     */
    public String constructDeleteMsg(Task task, int tasksSize) {
        String msg = "Noted meow. I've removed this task:\n" + String.format("  [%s][%s] %s\n", task.getTypeSymbol(),
                task.getStatusSymbol(), task.getDesc()) + String.format("Now you have %d tasks in the list.\n", tasksSize);

        return constructMsg(msg);

//        System.out.println(BORDER);
//        System.out.println("Noted meow. I've removed this task:");
//        System.out.printf("  [%s][%s] %s\n", task.getTypeSymbol(), task.getStatusSymbol(),
//                task.getDesc());
//        System.out.printf("Now you have %d tasks in the list.\n", tasksSize);
//        System.out.println(BORDER + "\n");
    }

    /**
     * Display the tasks from the search result
     *
     * @param tasks A list of tasks as search results
     */
    public String constructFoundMsg(List<Task> tasks) {
        String msg = "Meow, here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            msg += String.format("%d.[%s][%s] %s\n", i + 1, task.getTypeSymbol(), task.getStatusSymbol(),
                    task.getDesc());
        }

        return constructMsg(msg);

//        System.out.println(BORDER);
//        System.out.println("Meow, here are the matching tasks in your list:");
//        for (int i = 0; i < tasks.size(); i++) {
//            Task task = tasks.get(i);
//            System.out.printf("%d.[%s][%s] %s\n", i + 1, task.getTypeSymbol(), task.getStatusSymbol(), task.getDesc());
//        }
//        System.out.println(BORDER + "\n");
    }

    /**
     * Displays all tasks currently in the list
     *
     * @param tasks List of tasks
     */
    public String constructTaskList(List<Task> tasks) {
        String msg = "Meow, here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            msg += String.format("%d.[%s][%s] %s\n", i + 1, task.getTypeSymbol(), task.getStatusSymbol(),
                    task.getDesc());
        }

        return constructMsg(msg);

//        System.out.println(BORDER);
//        System.out.println("Meow, here are the tasks in your list:");
//        for (int i = 0; i < tasks.size(); i++) {
//            Task task = tasks.get(i);
//            System.out.printf("%d.[%s][%s] %s\n", i + 1, task.getTypeSymbol(), task.getStatusSymbol(), task.getDesc());
//        }
//        System.out.println(BORDER + "\n");
    }

    /**
     * Displays an error message
     *
     * @param msg Error message
     */
    public String constructErrorMsg(String msg) {
        return constructMsg(String.format("ERROR MEOW! %s", msg));
    }
}
