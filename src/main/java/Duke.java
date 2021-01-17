import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Duchess: Hello, Duchess here. What can i do for you?");

        String command = sc.nextLine();  // Read user input
        System.out.println("Duchess: " + command);
        while (!command.equals("bye")) {
            String nextCommand = sc.nextLine();
            command = nextCommand;
            System.out.println("Duchess: " + command);
        }
    }
}
