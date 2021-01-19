package duke.main;

import java.util.Scanner;

public class Duke {
    private static final String[] greet = {
            "Greetings! I'm Your Personal Assistant Duke:)",
            "What can I do for you today?"
    };
    private static final String[] exit = {
            "Bye. Nice to meet you and hope to see you again soon!"
    };

    /**
     * Main class for project duke.
     * Takes in an user command (within the exclusive list) and react accordingly.
     * @param args normal input as per other Java programmes
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(parseMessage(greet));
        String command = sc.nextLine();
        while(!command.equals("bye")) {
            String[] messages = { command };
            System.out.println(parseMessage(messages));
            command = sc.nextLine();
        }
        System.out.println(parseMessage(exit));
    }

    private static String parseMessage(String[] messages) {
        String border = "    ____________________________________________________________\n";
        String res = border;
        String indent = "     ";
        for(int i = 0; i < messages.length; i++) {
            res += indent + messages[i] + "\n";
        }
        res += border;
        return res;
    }
}
