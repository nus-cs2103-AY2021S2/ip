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
            command = scan.nextLine().trim();

            try {
                if (command.equals("bye")) {
                    System.out.println("Bye. See you soon!");
                } else if (command.equals("list")) {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.printf("%d. %s\n", i + 1, tasks.get(i));
                    }
                } else if (command.startsWith("done")) {
                    if (command.equals("done")) {
                        throw new VergilException(
                                "Sorry! 'done' requires typing the list number of the task.");
                    }
                    Task task = tasks.get(Integer.parseInt(command.split(" ")[1]) - 1);
                    if (task.isDone()) {
                        System.out.printf("You have already completed this task: %s\n",
                                task);
                    } else {
                        task.doTask();
                        System.out.println("Sweet! Task completed:");
                        System.out.printf("   %s\n", task);
                    }
                } else if (command.startsWith("todo")) {
                    if (command.equals("todo")) {
                        throw new VergilException(
                                "Sorry! 'todo' requires typing a description of the task.");
                    }
                    String desc = command.split(" ", 2)[1];
                    tasks.add(new Todo(desc));
                    System.out.printf("Added '%s' as a ToDo task.\n", desc);
                } else if (command.startsWith("deadline")) {
                    if (command.equals("deadline")) {
                        throw new VergilException(
                                "Sorry! 'deadline' requires typing a description of," +
                                " the keyword '/by', and a due time for the task.");
                    }
                    String[] bySplit = command.split(" /by ");
                    String desc = bySplit[0].split(" ", 2)[1];
                    String time = bySplit[1];

                    tasks.add(new Deadline(desc, time));
                    System.out.printf("Added '%s' as a deadline task by '%s'.\n",
                            desc,
                            time);
                } else if (command.startsWith("event")) {
                    if (command.equals("event")) {
                        throw new VergilException(
                                "Sorry! 'event' requires typing a description of," +
                                        " the keyword '/at', and a scheduled time for the task.");
                    }

                    String[] atSplit = command.split(" /at ");
                    String desc = atSplit[0].split(" ", 2)[1];
                    String time = atSplit[1];

                    tasks.add(new Event(desc, time));
                    System.out.printf("Added '%s' as an event task by '%s'.\n",
                            desc,
                            time);
                } else {
                    throw new VergilException("Sorry! I cannot resolve this command.");
                }
            } catch (VergilException e) {
                System.out.println(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Sorry! The command's format is invalid.");
            }
            System.out.println();
        } while (!command.equals("bye"));
    }
}
