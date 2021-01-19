package percy.ui;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintStream;
import java.io.InputStream;

import static percy.messages.Messages.*;
import percy.Task;

public class UserInterface {
    private Scanner in;
    private PrintStream out;

    private static final String INDENT = "    ";
    public static final String DIVIDER = "    ____________________________________________________________";


    public UserInterface(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public UserInterface() {
        this(System.in, System.out);
    }

    public String readCommand() {
        String command = in.nextLine();
        return command;
    }

    public void showDivider() {
        out.println(DIVIDER); // PrintStream class triggers println method
    }

    public void showStartUp() {
        out.println(MESSAGE_LOGO);
        out.println(DIVIDER);
        out.println(MESSAGE_WELCOME);
        out.println(DIVIDER);
        showBlankLine();
    }

    public void showBye() {
        System.out.println(DIVIDER);
        out.println(INDENT + MESSAGE_GOODBYE);
        System.out.println(DIVIDER);
    }

    public void showBlankLine() {
        out.println();
    }

    public void list(ArrayList<Task> list) {
        int i = 1;
        showDivider();
        for (Task t : list) {
            System.out.println(INDENT
                    + String.valueOf(i++)
                    + ". " + "["
                    + t.getStatusIcon() + "] "
                    + t.getDescription());
        }
        showDivider();
        showBlankLine();
    }

    public void add(String item) {
        System.out.println(DIVIDER);
        System.out.println(INDENT + "added " + item);
        System.out.println(DIVIDER);
        showBlankLine();
    }

    public void showEcho(String command) {
        System.out.println(DIVIDER);
        out.println(INDENT + command);
        System.out.println(DIVIDER);
        showBlankLine();
    }

    public void checkOff(Task t) {
        System.out.println(DIVIDER);
        System.out.println(INDENT + "Nice! I've marked this task as done:");
        System.out.println(INDENT + INDENT + "[" + t.getStatusIcon() + "] " + t.getDescription());
        System.out.println(DIVIDER);
        showBlankLine();
    }

}
