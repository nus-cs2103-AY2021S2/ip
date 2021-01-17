import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {

    public static Scanner sc = new Scanner(System.in);
    public static List<Task> database = new ArrayList<>();

    public static void main(String[] args) {
        // Print greeting
        printGreeting();

        // Ask for commands
        boolean isExiting = false;
        while (true) {
            String command = sc.next();
            printHorizontalLine();
            switch (command) {
                case "list":
                    if (database.isEmpty()) {
                        System.out.println("You do not have anything to do at the moment!");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (Task task : database) {
                            System.out.printf("%d.%s\n", task.getIndex(), task.toString());
                        }
                    }
                    break;
                case "todo":
                    String toDoTaskName = sc.nextLine().strip();
                    ToDoTask toDoTask = new ToDoTask(toDoTaskName);
                    database.add(toDoTask);
                    printAddedTask(toDoTask);
                    break;
                case "deadline":
                    String[] deadlineInputArr = sc.nextLine().split("/by");
                    String deadlineTaskName = deadlineInputArr[0].strip();
                    String deadline = deadlineInputArr[1].strip();
                    DeadlineTask deadlineTask = new DeadlineTask(deadlineTaskName, deadline);
                    database.add(deadlineTask);
                    printAddedTask(deadlineTask);
                    break;
                case "event":
                    String[] eventInputArr = sc.nextLine().split("/at");
                    String eventTaskName = eventInputArr[0].strip();
                    String eventTime = eventInputArr[1].strip();
                    EventTask eventTask = new EventTask(eventTaskName, eventTime);
                    database.add(eventTask);
                    printAddedTask(eventTask);
                    break;
                case "done":
                    String doneInput = sc.nextLine().strip();
                    int index = Integer.parseInt(doneInput);
                    Task task = database.get(index - 1);
                    task.completeTask();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.printf("  [X] %s\n", task.getName());
                    break;
                case "bye":
                    isExiting = true;
                    break;
                default:
                    System.out.println("Please enter a valid command!!");
            }
            if (isExiting) {
                break;
            }
            printHorizontalLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public static void printGreeting() {
        printHorizontalLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm");
        System.out.println(logo);
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    public static void printHorizontalLine() {
        for (int i = 0; i < 60; i++) {
            System.out.print('-');
        }
        System.out.println();
    }

    public static void printAddedTask(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.printf("Now you have %d tasks in your list.\n", database.size());
    }
}

