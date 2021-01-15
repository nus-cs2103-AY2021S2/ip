import java.util.Scanner;

public class Duke {

    /**
     *  Greeter for Duke
    */
    public static void greet() {
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";

        System.out.println("    ---------------------------------------");
        System.out.println(logo);
        System.out.println("    ---------------------------------------");
        System.out.println("    Hello! This is Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    ---------------------------------------");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();

        boolean done = false;
        while (!done) {
            String command = sc.next();
            System.out.println("    ---------------------------------------");
            if (command.equals("bye")) {
                done = true;
                System.out.println("Bye bye!");
            } else {
                System.out.println("    " + command);
            }
            System.out.println("    ---------------------------------------");
        }
    }
}
