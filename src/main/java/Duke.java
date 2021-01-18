import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void list(ArrayList<String> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + ": " + tasks.get(i));
        }
    }

    public static void blah() {
        System.out.println("     blah");
    }

    public static void bye() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String userInput;
        ArrayList<String> userTasks = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("     Hello from\n" + logo);
        System.out.println("     What can I do for you?");
        System.out.println("     _______________________________________\n");

        do {
            userInput = sc.nextLine();
            System.out.println("     _______________________________________");
            switch (userInput) {
                case "list":
                    list(userTasks);
                    break;
                case "blah":
                    blah();
                    break;
                case "bye":
                    bye();
                    break;
                default:
                    userTasks.add(userInput);
                    System.out.println("     added: " + userInput);
                    break;
            }
            System.out.println("     _______________________________________\n");

        } while(!userInput.equals("bye"));

    }
}
