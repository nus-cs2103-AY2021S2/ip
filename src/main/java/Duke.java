import tasks.Task;
import tasks.ToDoTask;
import tasks.EventTask;
import tasks.DeadlineTask;

import java.util.Scanner;

public class Duke {

    public static void printDivider() {
        String divider = "    ___________________________________________";
        System.out.println(divider);
    }

    public static void welcome() {
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
    }

    public static void printMessage(String message) {
        System.out.println("     " + message);
    }

    public static void bye() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        printDivider();
        welcome();
        printDivider();

        Scanner sc = new Scanner(System.in);

        boolean carryOn = true;
        TaskHandler handler = new TaskHandler();

        while (carryOn) {
            String action = sc.nextLine();
            String[] arr = action.split(" ");
            String description;
            int actionIndex;
            int descriptionIndex;

            switch(arr[0]) {
                case "todo":
                    printDivider();
                    handler.handleToDoTask(action);
                    printDivider();
                    break;
                case "deadline":
                    printDivider();
                    handler.handleDeadlineTask(action);
                    printDivider();
                    break;
                case "event":
                    printDivider();
                    handler.handleEventTask(action);
                    printDivider();
                    break;
                case "list":
                    printDivider();
                    handler.printStored();
                    printDivider();
                    break;
                case "done":
                    int number = Integer.valueOf(arr[1]);
                    printDivider();
                    handler.handleDone(number);
                    printDivider();
                    break;
                case "bye":
                    carryOn = false;
                    break;
                default:
                    Task task = new Task(action);
                    printDivider();
                    handler.add(task);
                    printDivider();
                    break;
            }
        }

        printDivider();
        bye();
        printDivider();

        sc.close();
    }
}
