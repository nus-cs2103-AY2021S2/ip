import java.util.Scanner;

public class Duke {
    public static void list() {
        System.out.println("     list");
    }

    public static void blah() {
        System.out.println("     blah");
    }

    public static void bye() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String userInput;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("     Hello from\n" + logo);
        System.out.println("     What can I do for you?");
        System.out.println("     _______________________________________\n");

        do {
            userInput = sc.nextLine();
            System.out.println("     _______________________________________");
            switch (userInput) {
                case "list":
                    list();
                    break;
                case "blah":
                    blah();
                    break;
                case "bye":
                    bye();
                    break;
                default:
                    break;
            }
            System.out.println("     _______________________________________\n");

        } while(!userInput.equals("bye"));

    }
}
