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
        System.out.println(WELCOME_MESSAGE);
    }

    public void printTasks(List<Task> taskList) {
        System.out.println(">>> Here are your tasks:");
        int i = 1;
        for( Task t : taskList) {
            System.out.printf("    %d: [%s] [%s] %s\n", i,
                    t.getTaskType(), t.getStatusIcon(), t.getTaskDescription());
            i++;
        }
    }
}
