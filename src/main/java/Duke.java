import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println(formatMessage("Hello! I'm Duke\n     What can I do for you?"));

        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        while (!input.equals("bye")) {
            System.out.println(formatMessage(input));
            input = sc.next();
        }

        System.out.println(formatMessage("Bye. Hope to see you again soon!"));
    }

    public static String formatMessage(String message) {
        return ("    ____________________________________________________________\n" + "     " + message + "\n"
                + "    ____________________________________________________________\n");
    }
}
