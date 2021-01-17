import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();

        Scanner sc = new Scanner(System.in);
        boolean toExit = false;
        while (!toExit) {
            String input = sc.nextLine();

            switch (input) {
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
            default:
                duke.addText(input);

                duke.printDivider();
                duke.indentedPrint("added: " + input);
                duke.printDivider();
                break;
            }
        }
    }
}
