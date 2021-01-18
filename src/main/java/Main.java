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

                try {
                    duke.checkArgument("done", input);
                } catch (DukeException de){
                    duke.printDivider();
                    duke.indentedPrint(de.getMessage());
                    duke.printDivider();
                    break;
                }

                int index = Integer.parseInt(arr[1]);
                duke.setTask(index, true);

                duke.printDivider();
                duke.indentedPrint("Nice! I've marked this task as done:");
                duke.indentedPrint(duke.getTask(index).toString());
                duke.printDivider();
                break;
            case "todo":

                try {
                    duke.checkArgument("todo", input);
                } catch (DukeException de){
                    duke.printDivider();
                    duke.indentedPrint(de.getMessage());
                    duke.printDivider();
                    break;
                }

                ToDo t = new ToDo(arr[1]);
                duke.addTask(t);

                duke.printDivider();
                duke.indentedPrint("Got it. I've added this task:");
                duke.indentedPrint(" " + t.toString());
                duke.indentedPrint("Now you have " + duke.getTasksSize() +
                        " task(s) in the list.");
                duke.printDivider();
                break;
            case "deadline":

                try {
                    duke.checkArgument("deadline", input);
                } catch (DukeException de){
                    duke.printDivider();
                    duke.indentedPrint(de.getMessage());
                    duke.printDivider();
                    break;
                }

                String[] dSplit = arr[1].split(" /by ");
                Deadline d = new Deadline(dSplit[0], dSplit[1]);
                duke.addTask(d);

                duke.printDivider();
                duke.indentedPrint("Got it. I've added this task:");
                duke.indentedPrint(" " + d.toString());
                duke.indentedPrint("Now you have " + duke.getTasksSize() +
                        " task(s) in the list.");
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
                        " task(s) in the list.");
                duke.printDivider();
                break;
            case "delete":
                try {
                    duke.checkArgument("delete", input);
                } catch (DukeException de){
                    duke.printDivider();
                    duke.indentedPrint(de.getMessage());
                    duke.printDivider();
                    break;
                }

                int index1 = Integer.parseInt(arr[1]);

                duke.printDivider();
                duke.indentedPrint("Noted. I've removed this task: ");
                duke.indentedPrint(" " + duke.getTask(index1).toString());
                duke.indentedPrint("Now you have " + (duke.getTasksSize() - 1) +
                        " task(s) in the list.");
                duke.printDivider();

                duke.deleteTask(index1);
                break;
            default:
                duke.printDivider();

                try {
                    duke.checkInput(input);
                } catch (DukeException de) {
                    duke.indentedPrint(de.getMessage());
                }

                duke.printDivider();
                break;
            }
        }
    }
}
