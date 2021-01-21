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
                        System.out.println("Sweet! Task completed:");
                        System.out.printf("   %s\n", task);
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Sorry! There is no task with that list number.");
                }
            } else if (command.matches("todo .+")) {
                String desc = command.split(" ", 2)[1];
                tasks.add(new Todo(desc));
                System.out.printf("Added '%s' as a ToDo task.\n", desc);
            } else if (command.matches("deadline .+ /by .+")) {
                String[] bySplit = command.split(" /by ");
                String desc = bySplit[0].split(" ", 2)[1];
                String time = bySplit[1];

                tasks.add(new Deadline(desc, time));
                System.out.printf("Added '%s' as a deadline task by '%s'.\n",
                        desc,
                        time);
            } else if (command.matches("event .+ /at .+")) {
                String[] atSplit = command.split(" /at ");
                String desc = atSplit[0].split(" ", 2)[1];
                String time = atSplit[1];

                tasks.add(new Event(desc, time));
                System.out.printf("Added '%s' as an event task by '%s'.\n",
                        desc,
                        time);
            } else {
                System.out.println("Sorry! This command is unrecognizable.");
            }
            System.out.println();
        } while (!command.equals("bye"));
    }
}
