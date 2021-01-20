import java.util.Scanner;

public class Duke {
    private final Scanner scanner;
    private boolean isActive;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();

    }

    public Duke() {
        isActive = true;
        scanner = new Scanner(System.in);

        greet();
        listen();
    }

    private void greet() {
        System.out.println("Hello, I'm Duke!\n" + "What can I do for you?\n");
    }

    private void listen() {
        while (isActive) {
            System.out.println("Let me know what to do!");
            String command = scanner.next();

            if (command.equals("bye")) {
                isActive = false;
                System.out.println("Bye bye, see you soon!\n");
            } else {
                System.out.println(command + "\n");
            }
        }
    }
}
