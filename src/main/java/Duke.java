import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    public static List<Task> inputList = new ArrayList<>();


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

    public static void printStored() {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < inputList.size(); i++) {
            Task task = inputList.get(i);
            System.out.println("      " + (i + 1) + ".[" + task.getStatusIcon()
                    +  "]" + task);
        }
    }

    public static void printMarked() {
        System.out.println("     " + "Nice! I've marked this task as done:");
    }

    public static void printSingleTask(int number) {
        inputList.get(number);
    }

    public static void main(String[] args) {
        printDivider();
        welcome();
        printDivider();

        Scanner sc = new Scanner(System.in);

        boolean carryOn = true;

        while (carryOn) {
            String action = sc.nextLine();
            String[] arr = action.split(" ");
            Task task = new Task(action);
            switch(arr[0]) {
                case "list":
                    printDivider();
                    printStored();
                    printDivider();
                    break;
                case "done":
                    int number = Integer.valueOf(arr[1]);
                    printDivider();
                    printMarked();
                    Task markDone = inputList.get(number - 1);
                    markDone.markAsDone();
                    markDone.getStatusAndTask();
                    printDivider();
                    break;
                case "bye":
                    carryOn = false;
                    break;
                default:
                    inputList.add(task);
                    printDivider();
                    printMessage("added: " + task);
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
