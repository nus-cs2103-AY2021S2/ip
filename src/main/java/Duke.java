import java.util.ArrayList;
import java.util.Scanner;

/**
 * Encompasses the behavior of the Duke chat-bot.
 */
public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Carries out the corresponding actions of the command.
     *
     * @param command A string of the command to be carried out.
     */
    private static void dispatch(String command) {
        printLine();
        switch (command.split(" ")[0]) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;
            case "list":
                if (tasks.size() == 0) {
                    System.out.println("Your list is empty!");
                } else {
                    for (int i = 1; i <= tasks.size(); i++) {
                        System.out.println(i + "." + tasks.get(i - 1));
                    }
                }
                break;
            case "done":
                findAndMarkTask(command);
                break;
            default:
                if (command.trim().length() > 0) {
                    System.out.println("added: " + command);
                    tasks.add(new Task(command));
                } else {
                    System.out.println("Please enter something ...");
                }
        }
        printLine();
    }

    /**
     * Finds and marks task specified in the command string. Handles errors that include index out of bounds and number format.
     *
     * @param command Command inputted into the console handled by dispatch method.
     */
    private static void findAndMarkTask(String command) {
        try {
            Task currTask = tasks.get(Integer.parseInt(command.split(" ")[1]) - 1);
            currTask.markTask();
            System.out.println("Swee chai. It's done.\n" + currTask);
        } catch (IndexOutOfBoundsException e) {
            if (tasks.size() == 0) {
                System.out.println("Your list is empty!");
            } else {
                System.out.println("The task number provided does not exist. :/");
            }
        } catch (Exception e) {
            if (tasks.size() == 0) {
                System.out.println("Your list is empty!");
            } else {
                System.out.println("Please enter a number from 1 to " + tasks.size());
            }
        }
    }

    private static void printLine() {
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
