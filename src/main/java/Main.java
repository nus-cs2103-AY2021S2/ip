import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();

        Scanner sc = new Scanner(System.in);
        boolean toExit = false;
        while (!toExit) {
            String input = sc.nextLine();
            String[] arr = input.split(" ", 2);

            // First word of input is used as switch argument.
            switch (arr[0]) {
            case "bye":
                duke.printDivider();
                duke.indentedPrint("Bye. Hope to see you again soon!");
                duke.printDivider();
                toExit = true;
                break;
            case "list":
                duke.printDivider();
                duke.iterateList();
                duke.printDivider();
                break;
            case "done":
                int index = Integer.parseInt(arr[1]);
                duke.setTask(index, true);

                duke.printDivider();
                duke.indentedPrint("Nice! I've marked this task as done:");
                duke.indentedPrint(duke.getTask(index).toString());
                duke.printDivider();
                break;
            case "todo":
                ToDo t = new ToDo(arr[1]);
                duke.addTask(t);

                duke.printDivider();
                duke.indentedPrint("Got it. I've added this task:");
                duke.indentedPrint(" " + t.toString());
                duke.indentedPrint("Now you have " + duke.getTasksSize() +
                        " tasks in the list.");
                duke.printDivider();
                break;
            case "deadline":
                String[] dSplit = arr[1].split(" /by ");
                Deadline d = new Deadline(dSplit[0], dSplit[1]);
                duke.addTask(d);

                duke.printDivider();
                duke.indentedPrint("Got it. I've added this task:");
                duke.indentedPrint(" " + d.toString());
                duke.indentedPrint("Now you have " + duke.getTasksSize() +
                        " tasks in the list.");
                duke.printDivider();
                break;
            case "event":
                String[] eSplit = arr[1].split(" /at ");
                Event e = new Event(eSplit[0], eSplit[1]);
                duke.addTask(e);

                duke.printDivider();
                duke.indentedPrint("Got it. I've added this task:");
                duke.indentedPrint(" " + e.toString());
                duke.indentedPrint("Now you have " + duke.getTasksSize() +
                        " tasks in the list.");
                duke.printDivider();
                break;
            default:
                duke.printDivider();
                duke.indentedPrint("Sorry, I don't quite understand.");
                duke.printDivider();
                break;
            }
        }
    }
}
