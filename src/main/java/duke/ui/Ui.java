package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private static final String INDENTATION = "    ";
    private static final String BORDER = INDENTATION + "--------------------------------"
            + "--------------------------------------";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String ADD_TASK_ACK = "Got it. I've added this task:";



    private final Scanner sc;
    private final PrintStream out;

    public Ui(InputStream in, PrintStream out) {
        this.sc = new Scanner(in);
        this.out = out;
    }

    public Ui() {
        this(System.in, System.out);
    }

    public void display(String... msg) {
        out.println(BORDER);
        for (String m : msg) {
            out.println(INDENTATION + m);
        }
        out.println(BORDER);
    }

    public void showGreeting() {
        display("Hello from \n" + LOGO, "How may I help you?");
    }

    public String readCommand() {
        return sc.nextLine().trim();
    }

    public void showError(String msg) {
        display(msg);
    }

    public void printAddTaskAck(Task t, TaskList tasks) {
        String taskCountMsg = "You now have " + tasks.getTasksSize() + " task(s) in the list.";
        display(ADD_TASK_ACK, " " + t.toString(), taskCountMsg);
    }

    public void printExitMsg() {
        display("Bye. Hope to see you again soon!");
    }

    public void printTaskList(TaskList tasks) {
        String[] msg = new String[tasks.getTasksSize()];
        for (int i = 0; i < tasks.getTasksSize(); i++) {
            msg[i] = (i + 1) + ". " + tasks.getTask(i + 1);
        }
        display(msg);
    }

    public void printDoneMsg(Task task) {
        String[] msg = new String[2];
        msg[0] = "Nice! I've marked this task as done.";
        msg[1] = task.toString();
        display(msg);
    }

    public void printDeleteMsg(Task task, TaskList tasks) {
        String[] msg = new String[3];
        msg[0] = "Noted. I've removed this task.";
        msg[1] = " " + task.toString();
        msg[2] = "Now you have " + tasks.getTasksSize() +
                " task(s) in the list.";
        display(msg);
    }

    public void printError(String msg) {
        display(msg);
    }

}
