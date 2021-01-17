import java.util.*;

public class Duke {
    static final String INDENT = "        ";
    static final String BORDER = INDENT + "__________________________________________________";
    public static void main(String[] args) {
        String logo = "      _       __  __ \n"
                    + "     | |     / _|/ _|  \n"
                    + "     | | ___| |_| |_   \n"
                    + " _   | |/ _ \\  _|  _|  \n"
                    + "| |__| |  __/ | | |    \n"
                    + " \\____/ \\___|_| |_|    \n";
        System.out.println(BORDER);
        System.out.println(INDENT + "Hello from\n" + logo);
        System.out.println(INDENT + "What can I do for you?");
        System.out.println(BORDER + "\n");

        Scanner sc = new Scanner(System.in);
        while(true) {
            String msg = sc.next();
            if(msg.equals("bye")) {
                System.out.println(BORDER);
                System.out.println(INDENT + "Bye. Hope to see you again!");
                System.out.println(BORDER + "\n");
                break;
            } else {
                System.out.println(BORDER);
                System.out.println(INDENT + msg);
                System.out.println(BORDER + "\n");
            }
        }
    }
}
