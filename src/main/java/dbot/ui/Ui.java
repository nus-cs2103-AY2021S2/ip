package dbot.ui;

import dbot.task.Task;
import dbot.tasklist.TaskList;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private final String NAME = "DougBot";
    private static final String DIVIDER = "===================================================";
    private final Scanner in;
    private final PrintStream out;
    private final PrintStream err;

    public Ui() {
        this(System.in, System.out, System.err);
    }

    public Ui(InputStream in, PrintStream out, PrintStream err) {
        this.in = new Scanner(in);
        this.out = out;
        this.err = err;
    }
    public void showLoadingError() {
        out.println("ERROR: Could not load from storage.");
    }

    public void showWelcome() {
        out.println("Welcome to " + NAME + ".");
    }

    public String getUserInput() {
        out.print("Enter command: ");
        String fullUserInput = in.nextLine();
        return fullUserInput.strip();
    }

    public void showLine() {
        out.println(DIVIDER);
    }

    public void printAddTask(Task task) {
        out.println("Added: " + task.toString());
    }

    public void printTasks(TaskList tasks) {
        for (Task task : tasks.getTasks()) {
            out.println(task.toString());
        }
    }

    public void printDone(Task doneTask) {
        out.println("Completed: " + doneTask.toString());
    }

    public void printDelete(Task deleteTask) {
        out.println("Deleted: " + deleteTask.toString());
    }

    public void showExitMessage() {
        out.println("Goodbye.");
    }

    public void showHelpMessage() {
        out.println("Use the following commands: list, todo, event DESC /at DATE, deadline DESC /by DATE");
    }

    public void showError(String message) {
        err.println(message);
    }
}
