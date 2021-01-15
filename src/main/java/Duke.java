import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    public static List<String> inputList = new ArrayList<>();


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
        for (int i = 0; i < inputList.size(); i++) {
            System.out.println("      " + (i + 1) + ". " + inputList.get(i));
        }
    }

    public static void main(String[] args) {
        printDivider();
        welcome();
        printDivider();

        Scanner sc = new Scanner(System.in);

        boolean carryOn = true;

        while (carryOn) {
            String action = sc.nextLine();

            switch(action) {
                case "list":
                    printDivider();
                    printStored();
                    printDivider();
                    break;
                case "bye":
                    carryOn = false;
                    break;
                default:
                    inputList.add(action);
                    printDivider();
                    printMessage("added: " + action);
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
