import java.util.ArrayList;
import java.util.Scanner;

/**
 * Encompasses the behavior of the Duke chat-bot.
 */
public class Duke {
    public static ArrayList<String> tasks = new ArrayList<>();

    /**
     * Carries out the corresponding actions of the command.
     * @param command A string of the command to be carried out.
     */
    public static void dispatch(String command) {
        printLine();
        switch (command) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;
            case "list":
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.println(i + ". " + tasks.get(i-1));
                }
                break;
            default:
                System.out.println("added: " + command);
                tasks.add(command);
        }
        printLine();
    }

    public static void printLine() {
        System.out.println("------------------------------");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");

        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            String command = scan.nextLine();
            if (command.equals("bye")) {
                dispatch(command);
                break;
            } else {
                dispatch(command);
            }
        }
        scan.close();
    }
}
