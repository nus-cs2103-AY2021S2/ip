import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        ArrayList<String> ls = new ArrayList<>();

        System.out.println(formatMessage("Hello! I'm Duke\nWhat can I do for you?"));

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printList(ls);
            } else {
                System.out.println(formatMessage("added: " + input));
                ls.add(input);
            }
            input = sc.nextLine();
        }

        System.out.println(formatMessage("Bye. Hope to see you again soon!"));
        sc.close();
    }

    // prints list item number and string
    public static void printList(ArrayList<String> ls) {
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < ls.size(); i++) {
            System.out.println("     " + (i + 1) + ". " + ls.get(i));
        }
        System.out.println("    ____________________________________________________________\n");
    }

    // format for greeting, echo and exit
    public static String formatMessage(String message) {
        String newMessage = message.replaceAll("\n", "\n     ");
        return ("    ____________________________________________________________\n" + "     " + newMessage + "\n"
                + "    ____________________________________________________________\n");
    }
}
