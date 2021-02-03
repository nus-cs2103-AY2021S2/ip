package duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

/**
 * Class to represent text UI of application.
 */
public class Ui {

    private static final String LINE_PREFIX = ">>> ";
    private static final String WELCOME_MESSAGE = "Hello Human, I am Bob.";

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
        out.println(WELCOME_MESSAGE);
    }

    public void printTasks(TaskList taskList) {
        out.println(">>> Ok Human. Here are your tasks:");
        int i = 1;
        for( Task t : taskList.getTaskList()) {
            out.printf("    %d: [%s] [%s] %s\n", i,
                    t.getTaskType(), t.getStatusIcon(), t.getTaskDescription());
            i++;
        }
    }

    public void printTaskDone(Task task) {
        out.printf(">>> Noted Human. I've marked this task as done:\n  [%s] [%s] %s\n",
                task.getTaskType(), task.getStatusIcon(),
                task.getTaskDescription());
    }

    public void printTaskDeleted(Task task) {
        out.printf("%s Task deleted successfully:\n  [%s] [%s] %s\n ",
                LINE_PREFIX, task.getTaskType(), task.getStatusIcon(),
                task.getTaskDescription());
    }
    
    public void printAddToDo(ToDo toDo) {
        System.out.printf("%s added:\n   [%s] [%s] %s\n",
                LINE_PREFIX, toDo.getTaskType(), toDo.getStatusIcon(),
                toDo.getTaskDescription());
        
    }

    public void printAddDeadLine(Deadline deadline) {
        System.out.printf("%s added:\n   [%s] [%s] %s (by: %s)\n",
                LINE_PREFIX, deadline.getTaskType(), deadline.getStatusIcon(),
                deadline.getTaskDescription(), deadline.getEndTime());
    }

    public void printAddEvent(Event event) {
        System.out.printf("%s added:\n   [%s] [%s] %s (at: %s)\n",
                LINE_PREFIX, event.getTaskType(), event.getStatusIcon(),
                event.getTaskDescription(), event.getEventTime());
    }


    public void showError(String errorMsg) {
        out.println("[ERROR]: " + errorMsg);
    }

    public void showLine() {
        out.println("-----------------------------");
    }

}
