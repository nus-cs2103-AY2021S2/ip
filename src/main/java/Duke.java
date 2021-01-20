import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String TEXT_INDENT = "     ";
        String DIVIDER = "    ____________________________________________________________";

        ArrayList<String> tasks = new ArrayList<String>();

        // initialize scanner
        Scanner sc = new Scanner(System.in);

        // welcome sequence
        System.out.println(DIVIDER);
        System.out.println(TEXT_INDENT + "Welcome to Duke!");
        System.out.println(TEXT_INDENT + "What can I do for you?");
        System.out.println(DIVIDER);

        // get user input
        String userInput = sc.nextLine();
        while (!userInput.toLowerCase().equals("bye")) {
            System.out.println(DIVIDER);
            // display list
            if (userInput.toLowerCase().equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    String item = TEXT_INDENT + String.valueOf(i + 1) + ". " + tasks.get(i);
                    System.out.println(item);
                }
            } else { // add task to list
                tasks.add(userInput);
                System.out.println(TEXT_INDENT + "added: " + userInput);
            }
            System.out.println(DIVIDER);
            userInput = sc.nextLine();
        }

        // exit sequence
        System.out.println(DIVIDER);
        System.out.println(TEXT_INDENT + "Bye! Hope you see you again :)");
        System.out.println(DIVIDER);
    }
}
