package duke;

import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.Scanner;

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

    public String showTasks(TaskList taskList) {
        int i = 1;
        StringBuilder response = new StringBuilder();
        for( Task t : taskList.getTaskList()) {
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

    public String showAllTasks(TaskList taskList) {
        String response = "";
        response += LINE_PREFIX + "Ok Human. Here are your tasks:\n";
        response += showTasks(taskList);
        return response;
    }

    public String showFoundTasks(TaskList taskList) {
        String response = "";
        response += LINE_PREFIX + "Ok Human. Here are the tasks I found:\n";
        response += showTasks(taskList);
        return  response;
    }

    public String showUpcomingTasks(TaskList taskList) {
        String response = "";
        response += LINE_PREFIX + "Ok Human. Here are your upcoming tasks:\n";
        response += showTasks(taskList);
        return  response;
    }

    public String printTaskDone(Task task) {
        return String.format("%sNoted Human. I've marked this task as done:\n[%s] [%s] %s\n",
                LINE_PREFIX, task.getTaskType(), task.getStatusIcon(),
                task.getTaskDescription());
    }

    public String showTaskDeleted(Task task) {
        return String.format(LINE_PREFIX + "Task deleted successfully:\n[%s] [%s] %s\n",
                task.getTaskType(), task.getStatusIcon(),
                task.getTaskDescription());
    }

    public String showAddToDo(ToDo toDo) {
        return String.format(LINE_PREFIX + "Added:\n[%s] [%s] %s\n",
                toDo.getTaskType(), toDo.getStatusIcon(),
                toDo.getTaskDescription());

    }

    public String showAddDeadLine(Deadline deadline) {
        LocalDateTime time = deadline.getEndTime();
        return String.format(LINE_PREFIX + "Added:\n[%s] [%s] %s (by: %s %s %s %s:%s)\n",
                deadline.getTaskType(), deadline.getStatusIcon(), deadline.getTaskDescription(),
                time.getMonth(), time.getDayOfMonth(), time.getYear(), time.getHour(),
                time.getMinute());
    }

    public String showAddEvent(Event event) {
        LocalDateTime time = event.getEventTime();
        return String.format(LINE_PREFIX + "Added:\n[%s] [%s] %s (at: %s %s %s %s:%s)\n",
                event.getTaskType(), event.getStatusIcon(), event.getTaskDescription(),
                time.getMonth(), time.getDayOfMonth(), time.getYear(), time.getHour(),
                time.getMinute());
    }



    public String showError(String errorMsg) {
        return "[ERROR] : " + errorMsg + "\n";
    }

    public String showLine() {
        return LINE_DIVIDER;
    }

}
