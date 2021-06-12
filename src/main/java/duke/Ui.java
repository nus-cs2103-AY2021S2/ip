package duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import duke.task.Task;



/**
 * Class to represent text UI of application.
 */
public class Ui {

    private static final int EXIT_DELAY = 3;
    private static final String LINE_PREFIX = "";
    private static final String WELCOME_MESSAGE = "Hello Human, I am B.O.B";
    private static final String EXIT_MESSAGE = "Goodbye Human. Exiting in " + EXIT_DELAY + " seconds...";
    private static final String LINE_DIVIDER = "-----------------------------\n";

    private final Scanner in;
    private final PrintStream out;

    /**
     * Wrapper constructor to be used.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructor to set standard input and output for Ui
     * @param in InputStream
     * @param out PrintStream
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public static float getExitDelay() {
        return EXIT_DELAY;
    }

    public String showWelcome() {
        return LINE_PREFIX + WELCOME_MESSAGE + "\n";
    }


    public String showBye() {
        return LINE_PREFIX + EXIT_MESSAGE + "\n";
    }

    /**
     *
      * @param taskList
     * @return
     */
    public String showTasksCount(TaskList taskList) {
        int count = taskList.getTasksCount();
        return (count == 1)
            ? "Current Total Tasks: " + count
                + " task"
            : "Current Total Tasks: " + count
                    + " tasks";

    }

    /**
     * Return all task in taskList as a string
     * @param taskList
     * @return
     */
    public String showTasks(TaskList taskList) {
        int i = 1;
        StringBuilder response = new StringBuilder();
        for (Task t : taskList.getTaskList()) {
            if (t.getTaskType().equals("TODO")) {
                response.append(String.format("%d: [%s] [%s] %s\n", i,
                        t.getTaskType(), t.getStatusIcon(), t.getTaskDescription()));
            } else {
                response.append(String.format("%d: %s\n", i, t));
            }

            i++;
        }

        return response.toString();
    }

    /**
     * Wrapper around showTask to add interactive sentence before the tasks
     * @param taskList
     * @return
     */
    public String showAllTasks(TaskList taskList) {
        String response = "";
        response += LINE_PREFIX + "Ok Human. Here are your tasks:\n";
        response += showTasks(taskList);
        return response;
    }

    /**
     * Show tasks found.
     * @param taskList
     * @return
     */
    public String showFoundTasks(TaskList taskList) {
        String response = "";
        response += LINE_PREFIX + "Ok Human. Here are the tasks I found:\n";
        response += showTasks(taskList);
        return response;
    }

    /**
     * Show upcoming tasks
     * @param taskList
     * @return
     */
    public String showUpcomingTasks(TaskList taskList) {
        String response = "";
        response += LINE_PREFIX + "Ok Human. Here are your upcoming tasks:\n";
        response += showTasks(taskList);
        return response;
    }

    /**
     * Show done tasks
     * @param task
     * @return
     */
    public String showTaskDone(Task task) {
        return String.format("%sNoted Human. I've marked this task as done:\n[%s] [%s] %s\n",
                LINE_PREFIX, task.getTaskType(), task.getStatusIcon(),
                task.getTaskDescription());
    }

    /**
     * Show deleted tasks
     * @param task
     * @return
     */
    public String showTaskDeleted(Task task, TaskList taskList) {
        return String.format(LINE_PREFIX + "Task deleted successfully:\n[%s] [%s] %s\n%s",
                task.getTaskType(), task.getStatusIcon(),
                task.getTaskDescription(), showTasksCount(taskList));
    }

    public String showAdd(Task task, TaskList taskList) {
        return String.format(LINE_PREFIX + "Added:\n%s\n%s\n", task, showTasksCount(taskList));
    }

    public String showError(String errorMsg) {
        return "[ERROR] : " + errorMsg + "\n";
    }

    public String showLine() {
        return LINE_DIVIDER;
    }

}
