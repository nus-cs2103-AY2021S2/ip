import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private final Scanner in;
    private final PrintStream out;
    private static final String DIVIDER_LINE = "____________________________________________________________\n";
    private static final String LIST_MESSAGE = "Here are the tasks in your list:";
    private static final String DONE_MESSAGE = "Nice! I've marked this task as done:";
    private static final String TASK_ADD_MESSAGE = "Got it. I've added this task:";
    private static final String TASK_COUNT_MESSAGE = "Tasks in list:";
    private static final String TASK_DELETE_MESSAGE = "Noted. I've removed this task:";
    private static final String TASK_FOUND_MESSAGE = "Matched Tasks:";
    private static final String LOADING_ERROR_MESSAGE = "Error in loading tasks from text file";
    private static final String GOODBYE_MESSAGE = "GOODBYE! Thanks for using DUKE";
    private static final String WELCOME_MESSAGE = "Hello from\n"
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n"
            + "\nHello! I'm Duke"
            + "\nWhat can I do for you?";

    public Ui(){
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String readCommand() {
        String fullInputLine = in.nextLine();
        return fullInputLine;
    }

    public void showToUser(String... stringToUser) {
        for (String s : stringToUser) {
            out.println(s.replace("\n", System.lineSeparator()));
        }
    }

    public void showWelcome() {
        showToUser(WELCOME_MESSAGE);
    }

    public void byeMsg() {
        showToUser(GOODBYE_MESSAGE);
    }

    public void showLoadingError() {
        showToUser(LOADING_ERROR_MESSAGE);
    }

    public void showLine() {
        showToUser(DIVIDER_LINE);
    }

    public void taskAddMsg(TaskList taskList) {
        String printStr = TASK_ADD_MESSAGE + "\n"
                + taskList.getTasksList().get(taskList.getTasksCount() - 1).toString() + "\n"
                + TASK_COUNT_MESSAGE
                + Integer.toString(taskList.getTasksCount());
        showToUser(printStr);
    }

    public void taskDelMsg(TaskList taskList, int delTaskNum) {
        String printStr = TASK_DELETE_MESSAGE + "\n"
                + taskList.getTasksList().get(delTaskNum - 1).toString() + "\n"
                + TASK_COUNT_MESSAGE
                + Integer.toString(taskList.getTasksCount() - 1);
        showToUser(printStr);
    }

    public void taskDoneMsg(TaskList taskList, int doneTaskNum) {
        String printStr = DONE_MESSAGE + "\n"
                + taskList.getTasksList().get(doneTaskNum - 1).toString();
        showToUser(printStr);
    }

    public void listMsg(TaskList taskList) {
        String printStr = LIST_MESSAGE + "\n"
                + taskList.toString();
        showToUser(printStr);
    }

    public void foundMsg(TaskList taskList) {
        String printStr = TASK_FOUND_MESSAGE + "\n"
                + taskList.toString();
        showToUser(printStr);
    }

    public void showError(String errorMsg) {
        showToUser(errorMsg);
    }






}
