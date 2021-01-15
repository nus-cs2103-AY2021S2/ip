import java.util.Scanner;

public class Duke {
    public static String GREETING = "Hi, I am Sonia! How can I help you?";

    public static void echo(String message) {
        System.out.println("Sonia: " + message);
    }

    public static void parse(String command) {
        if (command.equals("bye")) {
            echo("Goodbye!");
            System.exit(0);
        } else {
            echo(command);
        }
    }

    public static String prompt() {
        System.out.print("You: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static void main(String[] args) {
        echo(GREETING);
        while (true) {
            String command = prompt();
            parse(command);
        }
    }
}
