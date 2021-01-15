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
        String action = sc.nextLine();

        while (!action.equals("bye")) {
            printDivider();
            printMessage(action);
            printDivider();
            action = sc.nextLine();
        }

        printDivider();
        bye();
        printDivider();

        sc.close();
    }
}
