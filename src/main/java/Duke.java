import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String indent = "    ";
        String divider = "____________________________________________________________";

        ArrayList<String> tasks = new ArrayList<String>();

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
            if (userInput.toLowerCase().equals("list")) {
                System.out.println(indent + divider);
                for (int i = 0; i < tasks.size(); i++) {
                    String item = indent + " " + String.valueOf(i+1) + ". " + tasks.get(i);
                    System.out.println(item);
                }
                System.out.println(indent + divider);
                userInput = sc.nextLine();
            } else {
                tasks.add(userInput);
                System.out.println(indent + divider);
                System.out.println(indent + " added: " + userInput);
                System.out.println(indent + divider);
                userInput = sc.nextLine();
            }
        }

        // exit sequence
        System.out.println(indent + divider);
        System.out.println(indent + " Bye! Hope you see you again :)");
        System.out.println(indent + divider);
    }
}
