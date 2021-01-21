import java.util.Scanner;
import java.util.ArrayList;

public class Vergil {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String command;
        ArrayList<String> tasks = new ArrayList<>();

        System.out.println("Welcome! I'm Vergil!");
        System.out.println("How may I help you?");
        System.out.println();

        do {
            // User commands are prefixed with >>>.
            System.out.print(">>> ");
            command = scan.nextLine();

            switch (command) {
                // List the current tasks.
                case "list":
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                    break;

                // Exit the program.
                case "bye":
                    System.out.println("Bye. See you soon!");
                    break;

                // Add a task to the list of tasks.
                default:
                    tasks.add(command);
                    System.out.println("Added '" + command + "'.");
            }
            System.out.println();
        } while (!command.equals("bye"));
    }
}
