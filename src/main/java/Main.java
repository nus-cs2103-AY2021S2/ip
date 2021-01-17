import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();

        Scanner sc = new Scanner(System.in);
        boolean toExit = false;
        while (!toExit) {
            String input = sc.nextLine();
            String arr[] = input.split(" ");

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
            default:
                duke.addTask(input);

                duke.printDivider();
                duke.indentedPrint("added: " + input);
                duke.printDivider();
                break;
            }
        }
    }
}
