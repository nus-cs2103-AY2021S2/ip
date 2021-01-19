import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    ____________________________________________________________");
        System.out.println(logo);
        System.out.println("Hello! I'm Danh's Duke\nWhat can I do for you, Mr Danh?");
        System.out.println("    ____________________________________________________________\n");
        Scanner input = new Scanner(System.in);
        boolean signalToExit = false;
        while (!signalToExit && input.hasNextLine()) {
            String command = input.nextLine();
            echo(command);
            if (command.equals("bye")) {
                signalToExit = true;
            }
        }
    }

    public static void echo(String command) {
        System.out.println("    ____________________________________________________________");
        if (command.equals("bye")) {
            System.out.println("     Bye. Hope to see you again soon!");
        } else {
            System.out.println("     " + command);
        }
        System.out.println("    ____________________________________________________________\n");
    }
}
