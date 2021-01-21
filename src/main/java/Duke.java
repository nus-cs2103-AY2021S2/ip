import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greeting();
        while(true) {
            String command = sc.next();
            if (!processCommand(command)) {
                break;
            }
        }
        sc.close();
    }

    public static void printLine() {
        System.out.println("    ____________________________________________________________");
    }


    /**
     * print greeting message
     */
    public static void greeting() {
        printLine();
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        printLine();
    }

    /**
     * map commands to actions
     * @param command
     * @return false if command entered is "bye" else true
     */
    public static boolean processCommand(String command) {
        printLine();
        if (command.equals("bye")) {
            sayBye();
            printLine();
            return false;
        } else {
            echo(command);
            printLine();
            return true;
        }
    }

    public static void sayBye() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    public static void echo(String msg) {
        System.out.println("     " + msg);
    }
}
