import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "------------------------------------------------------------";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    
    private final Scanner in;
    private final PrintStream out;
    
    public Ui() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }
    
    public String getUserInput() {
        return in.nextLine();
    }
    
    public void printDivider() {
        show(DIVIDER);
    }

    public void printGreeting() {
        String welcomeMsg = String.format("Hello! I'm\n%s\nWhat can I do for you?", LOGO);
        show(DIVIDER, welcomeMsg);
    }

    public void printExitMessage() {
        show(EXIT_MESSAGE, DIVIDER);
    }
    
    public void print(String messageForUser) {
        show(messageForUser);
    }
    
    private void show(String... messages) {
        for (String msg : messages) {
            out.println(msg);
        }
    }
}
