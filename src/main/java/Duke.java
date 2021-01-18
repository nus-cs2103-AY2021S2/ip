import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String lineAfterCommand = "____________________________________________________________\n";
        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        System.out.println(lineAfterCommand + "Hello! I'm  Duke");
        System.out.println("What can I do for you?\n" + lineAfterCommand);
        String command = scanner.next();
        while (!command.equals("bye")) {
            System.out.println("\n" + lineAfterCommand + command + "\n" + lineAfterCommand);
            command = scanner.next();
        }
        System.out.println(lineAfterCommand + "Bye. Hope to see you again soon!\n" + lineAfterCommand);
    }
}
