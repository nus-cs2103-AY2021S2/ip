import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static String lines = "    ______________________________________________";
    private static String indent = "      ";

    private static void printTaskList() {
        System.out.println(lines);

        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(indent + (i + 1) + ". " + taskList.get(i));
        }

        System.out.println(lines);
    }

    // helper method to format chat bot responses
    // prints all strings in messages array in a separate indented line
    private static void print(String[] messages) {
        System.out.println(lines);

        for (int i = 0; i < messages.length; i++) {
            System.out.println(indent + messages[i]);
        }

        System.out.println(lines);
    }

    public static void main(String[] args) {
        String logo =
            " ______\n"
            + "/______\\ Kiwi's\n"
            + "|______|     Inn\n"
            + "####################";

        Scanner sc = new Scanner(System.in);

        // intro message
        System.out.println(logo);
        print(new String[]{"Welcome, traveller. I'm Kiwi.", "What would you like to do today?"});

        // echo their inputs until they say bye
        String userInput;
        String[] toPrint = new String[1];
        String bye = "bye";

        while (true) {
            userInput = sc.nextLine().trim();
            if (userInput.equals(bye)) {
                toPrint[0] = "Bye. See you again soon!";
                print(toPrint);
                sc.close();
                break;
            } else if (userInput.equals("list")) {
                printTaskList();
            } else {
                // todo add to list
                taskList.add(new Task(userInput));
                toPrint[0] = "added: " + userInput;
                print(toPrint);
            }
        }
    }
}
