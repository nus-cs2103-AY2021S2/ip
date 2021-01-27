import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

import static java.lang.System.exit;

public class Duke {
    public static ArrayList<Task> ls = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String partition = "\n/**********************************************************/\n";

        System.out.println(logo + "\nHello! I'm Duke.\n" + "What can I do for you?\n" + partition);
        command = sc.next();

        while(!command.equals("bye")) {
            try {
                switch (command) {
                    case "list":
                        printTasks();
                        System.out.println(partition);
                        break;
                    case "done":
                        int num = sc.nextInt();
                        markAsDone(num);
                        System.out.println(partition);
                        break;
                    case "todo":
                        addTask(new Todo(sc.nextLine()));
                        System.out.println(partition);
                        break;
                    case "deadline":
                        String inst1 = sc.nextLine();
                        String[] part1 = inst1.split("/by");
                        addTask(new Deadline(part1[0], LocalDate.parse(part1[1].trim())));
                        System.out.println(partition);
                        break;
                    case "event":
                        String inst2 = sc.nextLine();
                        String[] part2 = inst2.split("/at");
                        addTask(new Event(part2[0], LocalDate.parse(part2[1].trim())));
                        System.out.println(partition);
                        break;
                    case "delete":
                        int del = sc.nextInt();
                        deleteTask(del);
                        System.out.println(partition);
                        break;
                    default:
                        addTask(new Task(null));
                        System.out.println(partition);
                }
            } catch (DukeException de) {
                System.out.println(de.getMessage() + "\n" + partition);
            } catch (DateTimeParseException de) {
                System.out.println("Input date/time cannot be parsed: " + de.getMessage() + "\n" + partition);
            }

            command = sc.next();
        }

        System.out.println("Bye. Hope to see you again soon!\n" + logo);
        sc.close();
        exit(0);
    }

    public static void addTask(Task task) {
        ls.add(task);
        System.out.println("Got it. I've added this task:\n" + task.getStatus());
        printNumTasks();
    }

    public static void printTasks() {
        for (int i = 1; i <= ls.size(); i++) {
            System.out.println("" + i + ". " + ls.get(i - 1).getStatus());
        }
    }

    public static void markAsDone(int taskNum) {
        ls.get(taskNum - 1).markDone();
        System.out.println("This task is marked as done:\n" + ls.get(taskNum - 1).getStatus());
    }

    public static void deleteTask(int taskNum) {
        System.out.println("Noted. This task has been removed:\n" + ls.get(taskNum - 1).getStatus());
        ls.remove(taskNum - 1);
        printNumTasks();
    }

    public static void printNumTasks() {
        System.out.println("Now you have " + ls.size() + " tasks in the list.");
    }
}
