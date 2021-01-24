import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "---------------------------------------------------------------------------";

    private final Scanner in;
    private final PrintStream out;
    public String username;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Greets the user when the Duke is launched.
     */
    public void welcomeMsg() {
        out.println(DIVIDER);
        out.println("Hello! I'm Jay!\n" + "What is your name!");
        out.println(DIVIDER);
    }

    /**
     * Greets the user again after knowing the user's name.
     */
    public void nameMsg() {
        this.username = in.nextLine();
        out.println(DIVIDER);
        out.println("Hi " + this.username + "!");
        out.println("What can I do for you?");
        out.println(DIVIDER);
    }

    public void prompt() {
        out.print(this.username + ": ");
    }

    public void byeMsg() {
        out.println(DIVIDER);
        out.println("Bye " + this.username + "! Hope to see you again soon!");
        out.println(DIVIDER);
    }
}
