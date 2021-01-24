import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "---------------------------------------------------------------------------";

    private final Scanner in;
    private final PrintStream out;

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
        System.out.println(DIVIDER);
        System.out.println("Hello! I'm Jay!\n" + "What is your name!");
        System.out.println(DIVIDER);
    }

    /**
     * Greets the user again after knowing the user's name.
     */
    public void nameMsg() {
        String username = in.nextLine();
        System.out.println(DIVIDER);
        System.out.println("Hi " + username + "!");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);
    }
}
