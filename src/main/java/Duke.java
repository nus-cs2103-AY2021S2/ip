import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greet = "    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n";
        System.out.println(greet);

        Scanner sc = new Scanner(System.in);
        echo(sc);
        sc.close();
    }

    public static void echo(Scanner sc) {
        String cmd = sc.next();
        System.out.println("    ____________________________________________________________");
        if(!cmd.equals("bye")) {
            System.out.println("     " + cmd + "\n"
                    + "    ____________________________________________________________\n");
            echo(sc);
        } else {
            System.out.println("     Bye. Hope to see you again soon!" + "\n"
                    + "    ____________________________________________________________\n");
        }
    }
}
