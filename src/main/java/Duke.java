import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String indent = "    ";
        String divider = "____________________________________________________________";

        // initialize scanner
        Scanner sc = new Scanner(System.in);

        // welcome sequence
        System.out.println(indent + divider);
        System.out.println(indent + " Welcome to Duke!");
        System.out.println(indent + " What can I do for you?");
        System.out.println(indent + divider);

        // get user input
        String userInput = sc.nextLine();
        while (!userInput.toLowerCase().equals("bye")) {
            System.out.println(indent + divider);
            System.out.println(indent + userInput);
            System.out.println(indent + divider);
            userInput = sc.nextLine();
        }

        // exit sequence
        System.out.println(indent + divider);
        System.out.println(indent + " Bye! Hope you see you again :)");
        System.out.println(indent + divider);
    }
}
