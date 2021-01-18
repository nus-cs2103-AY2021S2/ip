package percy.ui;

import java.util.Scanner;
import java.io.PrintStream;
import java.io.InputStream;

import static percy.messages.Messages.*;

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
        out.println(INDENT + MESSAGE_GOODBYE);
    }

    public void showBlankLine() {
        out.println();
    }

    public void showEcho(String command) {
        out.println(INDENT + command);
    }

}
