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

    private static final String LINE_PREFIX = ">>> ";
    private static final String WELCOME_MESSAGE = "Hello Human, I am Bob.";
    private static final String EXIT_MESSAGE = "Goodbye Human. See You Again.";

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
     * @param in
     * @param out
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getUserCommand() {
        out.print(LINE_PREFIX);
        String rawInput = in.nextLine();
        return rawInput;
    }

    public void showWelcome() {
        out.println(LINE_PREFIX + WELCOME_MESSAGE);
    }


    public void showBye() {
        out.println(LINE_PREFIX + EXIT_MESSAGE);
    }

    public void printTasks(TaskList taskList) {
        int i = 1;
        for( Task t : taskList.getTaskList()) {
            if (t.getTaskType().equals("TODO")) {
                out.printf("    %d: [%s] [%s] %s\n", i,
                        t.getTaskType(), t.getStatusIcon(), t.getTaskDescription());
            } else {
                out.printf("    %d: %s", i, t);
            }

            i++;
        }
    }

    public void printAllTasks(TaskList taskList) {
        out.println(LINE_PREFIX + "Ok Human. Here are your tasks:");
        printTasks(taskList);
    }

    public void printFoundTasks(TaskList taskList) {
        out.println(LINE_PREFIX + "Ok Human. Here are the tasks I found:");
        printTasks(taskList);
    }

    public void printTaskDone(Task task) {
        out.printf("%s Noted Human. I've marked this task as done:\n  [%s] [%s] %s\n",
                LINE_PREFIX, task.getTaskType(), task.getStatusIcon(),
                task.getTaskDescription());
    }

    public void printTaskDeleted(Task task) {
        out.printf("%s Task deleted successfully:\n  [%s] [%s] %s\n",
                LINE_PREFIX, task.getTaskType(), task.getStatusIcon(),
                task.getTaskDescription());
    }

    public void printAddToDo(ToDo toDo) {
        System.out.printf("%s added:\n   [%s] [%s] %s\n",
                LINE_PREFIX, toDo.getTaskType(), toDo.getStatusIcon(),
                toDo.getTaskDescription());

    }

    public void printAddDeadLine(Deadline deadline) {
        LocalDateTime time = deadline.getEndTime();
        System.out.printf("%s added:\n   [%s] [%s] %s (by: %s %s %s %s:%s)\n",
                LINE_PREFIX, deadline.getTaskType(), deadline.getStatusIcon(),
                deadline.getTaskDescription(), time.getMonth(),
                time.getDayOfMonth(), time.getYear(),
                time.getHour(), time.getMinute());
    }

    public void printAddEvent(Event event) {
        LocalDateTime time = event.getEventTime();
        System.out.printf("%s added:\n   [%s] [%s] %s (at: %s %s %s %s:%s)\n",
                LINE_PREFIX, event.getTaskType(), event.getStatusIcon(),
                event.getTaskDescription(), time.getMonth(),
                time.getDayOfMonth(), time.getYear(),
                time.getHour(), time.getMinute());
    }



    public void showError(String errorMsg) {
        out.println("[ERROR]: " + errorMsg);
    }

    public void showLine() {
        out.println("-----------------------------");
    }

}
