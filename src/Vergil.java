import java.util.Scanner;
import java.util.ArrayList;

public class Vergil {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String command;
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("Welcome! I'm Vergil!");
        System.out.println("How may I help you?");
        System.out.println();

        do {
            // User commands are prefixed with >>>.
            System.out.print(">>> ");
            command = scan.nextLine();

            // List the current tasks.
            if (command.matches("bye")) {
                System.out.println("Bye. See you soon!");
            } else if (command.matches("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("%d. %s\n", i + 1, tasks.get(i));
                }
            } else if (command.matches("done \\d")) {
                try {
                    Task task = tasks.get(Integer.parseInt(command.split(" ")[1]) - 1);
                    if (task.isDone()) {
                        System.out.printf("You have already completed this task: %s\n",
                                task);
                    } else {
                        task.doTask();
                        System.out.printf("Sweet! Task completed: %s\n", task);
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Sorry! There is no task with that list number.");
                }
            } else {
                tasks.add(new Task(command));
                System.out.printf("Added '%s'.\n", command);
            }
            System.out.println();
        } while (!command.equals("bye"));
    }
}
