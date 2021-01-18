import java.util.Scanner;

public class Duke {

    // helper method to format chat bot responses
    // prints all strings in messages array in a separate indented line
    public static void print(String[] messages) {
        String lines = "    ______________________________________________";
        String indent = "      ";
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
            userInput = sc.nextLine();
            if (userInput.equals(bye)) {
                toPrint[0] = "Bye. See you again soon!";
                print(toPrint);
                sc.close();
                break;
            } else {
                toPrint[0] = userInput;
                print(toPrint);
            }
        }
    }
}
